package com.chatBot.telegramConverter.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MenuForChat {

    private final List<KeyboardRow> keyboardRows = new ArrayList<>();

    public List<KeyboardRow> keyboardForBanks() {

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("PrivatBank"));
        keyboardFirstRow.add(new KeyboardButton("OshadBank"));
        keyboardFirstRow.add(new KeyboardButton("money24.kharkov.ua"));
        keyboardRows.add(keyboardFirstRow);

        return keyboardRows;
    }

    public List<KeyboardRow> keyboardForValues() {

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("USD"));
        keyboardFirstRow.add(new KeyboardButton("EUR"));
        keyboardFirstRow.add(new KeyboardButton("RUR"));
        keyboardRows.add(keyboardFirstRow);

        return keyboardRows;
    }
}
