package com.chatBot.telegramConverter.telegramBot;

import com.chatBot.telegramConverter.domain.User;
import com.chatBot.telegramConverter.service.UserService;
import com.chatBot.telegramConverter.service.ValueService;
import com.chatBot.telegramConverter.utils.MenuForChat;
import com.chatBot.telegramConverter.utils.ParserSiteForMoney24;
import com.chatBot.telegramConverter.utils.ParserSiteForOshadBankData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    static Log log = LogFactory.getLog(ChatBot.class.getName());

    private final UserService userService;
    private final ValueService valueService;

    @Autowired
    public ChatBot(UserService userService, ValueService valueService) {
        this.userService = userService;
        this.valueService = valueService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {

            if (!userService.checkAvailability(message.getFrom().getId())) {
                userService.addUserOrSave(new User(
                        message.getFrom().getId(),
                        message.getFrom().getFirstName(),
                        message.getFrom().getLastName()

                ));
                log.info("Saved new user with id = " +  message.getFrom().getId());
            }

            User user = userService.findByTelegramId(message.getFrom().getId());

            switch (message.getText()) {
                case "/start":
                    log.info("user with id = " + user.getTelegramId() + " /start ");

                    user = userService.findByTelegramIdAndSetValue(user.getTelegramId(), null);
                    sendMessage(message, "Добрый день, " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " ! \n" +
                            "Выберите валюту, которую будем искать \n" +
                            "доллар, эвро, российский рубль.", user);
                    break;
                case "EUR":
                    log.info("user with id = " + user.getTelegramId() + " choose 'EUR' ");

                    User user1 = userService.findByTelegramIdAndSetValue(user.getTelegramId(), "EUR");
                    sendMessage(message, "Выберите источник данных:", user1);
                    break;
                case "USD":
                    log.info("user with id = " + user.getTelegramId() + " choose 'USD' ");

                    user = userService.findByTelegramIdAndSetValue(user.getTelegramId(), "USD");
                    sendMessage(message, "Выберите источник данных:", user);
                    break;
                case "RUR":
                    log.info("user with id = " + user.getTelegramId() + " choose 'RUR' ");
                    user = userService.findByTelegramIdAndSetValue(user.getTelegramId(), "RUR");

                    sendMessage(message, "Выберите источник данных:", user);

                    break;
                case "PrivatBank":
                    log.info("user with id = " + user.getTelegramId() + " choose 'PrivatBank'  with" + user.getSelectedValue());
                    sendMessage(message,
                            valueService.getRateForPrivatBank(user.getSelectedValue()).toString(), user);

                    userService.addUserOrSave(user);
                    break;
                case "OshadBank":
                    log.info("user with id = " + user.getTelegramId() + " choose 'OshadBank'  with" + user.getSelectedValue());

                    ParserSiteForOshadBankData parser = new ParserSiteForOshadBankData();
                    String value = parser.getRateBySelectedValue(user.getSelectedValue()).toString();

                    user.setSelectedValue(null);

                    sendMessage(message, value, user);

                    userService.addUserOrSave(user);
                    break;

                case "money24.kharkov.ua":

                    log.info("user with id = " + user.getTelegramId() + " choose 'money24.kharkov.ua'  with" + user.getSelectedValue());


                    ParserSiteForMoney24 parser1 = new ParserSiteForMoney24();
                    String value1 = parser1.getRateBySelectedValue(user.getSelectedValue()).toString();

                    user.setSelectedValue(null);

                    sendMessage(message, value1, user);

                    userService.addUserOrSave(user);
                    break;

            }

        }

    }

    public void sendMessage(Message message, String text, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        MenuForChat menu = new MenuForChat();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        if (user.getSelectedValue() != null) {
            keyboardRows.addAll(menu.keyboardForBanks());

            userService.addUserOrSave(user);
        } else {
            keyboardRows.addAll(menu.keyboardForValues());
        }

        try {
            setMenuButtons(sendMessage, keyboardRows);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void setMenuButtons(SendMessage sendMessage, List<KeyboardRow> menuRows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(menuRows);


    }

}
