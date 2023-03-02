package com.musinsa.url;

import com.musinsa.url.service.ShortUrlService;
import com.musinsa.url.web.dto.ShortUrlReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final ShortUrlService shortUrlService;
    /**
     * 초기 앱 구동시 단축URL 생성
     */
    @Override
    public void run(ApplicationArguments args) {
        shortUrlService.getOrCreateShortUrl(
                new ShortUrlReqDto("http://localhost:8080", "/v1/short-urls/table")
        );
    }
}
