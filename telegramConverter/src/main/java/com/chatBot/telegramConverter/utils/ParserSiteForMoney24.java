package com.chatBot.telegramConverter.utils;

import com.chatBot.telegramConverter.domain.Сurrency;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParserSiteForMoney24 {

    static Log log = LogFactory.getLog(ParserSiteForMoney24.class.getName());

    private static final String url = "https://money24.kharkov.ua";
    private static final String usdBuy = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[1]/div[2]/div[1]/div[1]/div[1]/strong";
    private static final String usdSell = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[1]/div[2]/div[1]/div[1]/div[2]/strong";
    private static final String euroBuy = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/strong";
    private static final String euroSell = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/strong";
    private static final String rusRubBuy = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[4]/div[2]/div[1]/div[1]/div[1]/strong";
    private static final String rusRubSell = "/html/body/div[1]/section[1]/div/div[2]/div[1]/div/div/div/div[4]/div[2]/div[1]/div[1]/div[2]/strong";


    private Сurrency parse(String value, String buyXpath, String sellXpath) {
        log.info("start parsing Money24 value = " + value);
        ParseSiteUtils utils = new ParseSiteUtils();
        Сurrency gotValue = utils.parse(value, url, buyXpath, sellXpath, true);
        log.info("parsed Money24 value = " + gotValue.toString());
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
