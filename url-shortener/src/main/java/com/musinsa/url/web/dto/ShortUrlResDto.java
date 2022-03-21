package com.musinsa.url.web.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlResDto {
    private String url;
    private String originUrl;
    private Long reqCount;
}
