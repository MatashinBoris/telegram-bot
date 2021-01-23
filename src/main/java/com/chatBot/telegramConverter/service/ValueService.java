package com.chatBot.telegramConverter.service;

import com.chatBot.telegramConverter.api.PrivatBankApi;
import com.chatBot.telegramConverter.domain.Сurrency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueService {

    public Сurrency getRateForPrivatBank(String nameOfValue) {
        PrivatBankApi privatBankApi = new PrivatBankApi();

        Сurrency findValue = new Сurrency();
        List<Сurrency> values = privatBankApi.routeToPrivatBank();
        for (Сurrency value : values) {
            System.out.println(value.toString());

            if (value.getCcy().equals(nameOfValue)) {
                findValue = value;
            }

        }
        return findValue;
    }
}
