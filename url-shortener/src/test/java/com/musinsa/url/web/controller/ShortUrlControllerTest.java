package com.musinsa.url.web.controller;

import com.musinsa.url.service.ShortUrlService;
import com.musinsa.url.web.dto.ShortUrlReqDto;
import com.musinsa.url.web.dto.ShortUrlResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = ShortUrlController.class
        )
class ShortUrlControllerTest extends BasicControllerTest{
    @MockBean
    ShortUrlService shortUrlService;

    @DisplayName("단축 URL 생성 페이지 성공")
    @Test
    public void SHORT_URL_FORM_VIEW_SUCCESS() throws Exception{
        mockMvc.perform(get("/v1/short-url"))
                .andExpect(status().isOk())
                .andExpect(view().name("short-url-form.html"));
    }

    @DisplayName("단축 URL 생성 성공")
    @Test
    public void SHORT_URL_CREATE_SUCCESS() throws Exception {
        //given
        String domain= "https://www.musinsa.com";
        String pathParm = "/?m=magazine&tvMode=on&uid=16027";
        String shortUrlKey = "/cdG1";
        Long reqCount = 1L;
        ShortUrlReqDto req = new ShortUrlReqDto(domain,pathParm);
        ShortUrlResDto res = new ShortUrlResDto(domain+shortUrlKey,domain+pathParm,reqCount);

        given(this.shortUrlService.getOrCreateShortUrl(any(ShortUrlReqDto.class))).willReturn(res);

        //when
        ResultActions result = mockMvc.perform(post("/v1/short-url")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(req)));

        //then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.url").value(domain+shortUrlKey))
                .andExpect(jsonPath("$.originUrl").value(domain+pathParm))
                .andExpect(jsonPath("$.reqCount").value(reqCount));
    }
}
