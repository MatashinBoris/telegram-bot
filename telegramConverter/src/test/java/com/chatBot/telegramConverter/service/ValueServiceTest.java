package com.chatBot.telegramConverter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValueServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ValueService valueService;

    @Test
    public void getRateForPrivatBankTest() throws Exception{

        assertThat(valueService).isNotNull();
    }
}