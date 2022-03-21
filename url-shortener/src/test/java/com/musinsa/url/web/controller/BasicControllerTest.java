package com.musinsa.url.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Disabled
public abstract class BasicControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    protected ObjectMapper objectMapper;


    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .alwaysDo(print())
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) //application/json;charset=UTF-8
                .build();
    }
}
