package com.musinsa.url.web.dto;

import com.musinsa.url.repository.ShortUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ShortUrlReqDto {
    @NotBlank(message = "domain is not empty")
    private String domain;
    @NotBlank(message = "pathParam is not empty")
    private String pathParam;

    public String getOriginUrl() {
        return this.domain + this.pathParam;
    }

    public ShortUrl createShortUrl() {
        return ShortUrl.builder()
                .originUrl(this.domain + this.pathParam)
                .reqCount(1L)
                .build();
    }
}
