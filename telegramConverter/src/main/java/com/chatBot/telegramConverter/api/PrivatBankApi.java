package com.chatBot.telegramConverter.api;

import com.chatBot.telegramConverter.domain.Сurrency;
import com.chatBot.telegramConverter.telegramBot.ChatBot;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PrivatBankApi {
    static Log log = LogFactory.getLog(PrivatBankApi.class.getName());
    private final String URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=3";

    public List<Сurrency> routeToPrivatBank() {

        RestTemplate restTemplate = new RestTemplate();
        log.info("Get req. to PrivatBank");
        ResponseEntity<List<Сurrency>> valueResponse =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Сurrency>>() {
                });
        log.info("response from PrivatBank " + valueResponse.getBody());
        return valueResponse.getBody();

    }

}
