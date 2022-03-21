package com.musinsa.url.web.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ShortUrlResDto {
    private String url;
    private String originUrl;
    private Long reqCount;
}
