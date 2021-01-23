package com.chatBot.telegramConverter.utils;

import com.chatBot.telegramConverter.domain.Сurrency;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ParserSiteForOshadBankData {
    static Log log = LogFactory.getLog(ParserSiteForOshadBankData.class.getName());
    private final String url = "https://finance.i.ua/bank/8/";
    private final String usdBuy = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[1]/td[1]/span/span[1]";
    private final String usdSell = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[1]/td[2]/span/span[1]";
    private final String euroBuy = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[2]/td[1]/span/span[1]";
    private final String euroSell = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/span/span[1]";
    private final String rusRubBuy = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[3]/td[1]/span/span[1]";
    private final String rusRubSell = "/html/body/div[3]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/table/tbody/tr[3]/td[2]/span/span[1]";


    private Сurrency parse(String value, String buyXpath, String sellXpath) {
        log.info("start parsing OshadBank value = " + value);
        ParseSiteUtils utils = new ParseSiteUtils();
        Сurrency gotValue = utils.parse(value, url, buyXpath, sellXpath, false);
        log.info("parsed OshadBank value = " + gotValue.toString());
        return gotValue;
    }

    public Сurrency getRateBySelectedValue(String value) {
        if (value.equals("USD")) {
            return parse("USD", usdBuy, usdSell);
        } else if (value.equals("EUR")) {
            return parse("EUR", euroBuy, euroSell);
        } else {
            return parse("RUR", rusRubBuy, rusRubSell);
        }
    }

}
