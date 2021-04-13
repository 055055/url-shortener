package com.musinsa.url.service;

import com.musinsa.url.web.dto.ShortUrlReqDto;
import com.musinsa.url.web.dto.ShortUrlResDto;

import java.util.List;

public interface ShortUrlService {
    ShortUrlResDto getOrCreateShortUrl(ShortUrlReqDto req);

    String findOriginUrlByShortUrlKey(String shortUrl);

    List<ShortUrlResDto> getAllShortUrl();

}
