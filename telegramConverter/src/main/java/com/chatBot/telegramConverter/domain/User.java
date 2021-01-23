package com.chatBot.telegramConverter.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private Integer telegramId;
    private String firstName;
    private String secondName;

    private String selectedValue;

    public User(){

    }

    public User(Integer telegramId, String firstName, String secondName) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Integer getTelegramId() {
        return telegramId;
    }


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
}
