package com.chatBot.telegramConverter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Сurrency implements Serializable {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

    public Сurrency() {
    }

    public Сurrency(String ccy, String buy, String sale) {
        this.ccy = ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getCcy() {
        return ccy;
    }

    @Override
    public String toString() {
        return "ваша валюта " + ccy + " к гривне, покупка по " + buy + " продажа по " + sale;
    }
}
