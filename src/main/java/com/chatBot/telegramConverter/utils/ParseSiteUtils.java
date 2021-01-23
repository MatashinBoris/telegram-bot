package com.chatBot.telegramConverter.utils;

import com.chatBot.telegramConverter.domain.Сurrency;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParseSiteUtils {
    static Log log = LogFactory.getLog(ParseSiteUtils.class.getName());
    public Сurrency parse(String value, String url, String buyXpath, String sellXpath, boolean enableJS) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);

        client.getOptions().setJavaScriptEnabled(enableJS);

        HtmlPage page = null;

        try {
            page = client.getPage(url);
        } catch (Exception e) {
            log.error("Error parsing site " + e.getMessage() + " url " + url);
            e.printStackTrace();
        }
        assert page != null;

        HtmlElement buyValue = page.getFirstByXPath(buyXpath);
        HtmlElement sellValue = page.getFirstByXPath(sellXpath);

        String buyValuePrice = buyValue.asText();
        String sellValuePrice = sellValue.asText();

        return new Сurrency(value, buyValuePrice, sellValuePrice);

    }
}
