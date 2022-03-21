package com.musinsa.url.repository;

import com.musinsa.error.CommonError;
import com.musinsa.error.CommonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ShortUrlRepositoryTest {
    @Autowired
    ShortUrlRepository shortUrlRepository;

    @DisplayName("단축 URL 엔티티저장 성공 ")
    @Test
    public void SHORT_URL_SAVE_SUCCESS() {
        //given
        String domain = "https://www.musinsa.com";
        String pathParm = "/?m=magazine&tvMode=on&uid=16027";
        String shortUrlKey = "/cdG1";
        Long reqCount = 1L;

        //given
        ShortUrl shortUrl = ShortUrl.builder()
                .originUrl(domain + pathParm)
                .reqCount(reqCount)
                .url(domain + shortUrlKey)
                .key(shortUrlKey)
                .build();

        //when
        ShortUrl save = shortUrlRepository.save(shortUrl);

        //then
        assertThat(save.getUrl()).isEqualTo(domain + shortUrlKey);
        assertThat(save.getOriginUrl()).isEqualTo(domain + pathParm);
        assertThat(save.getKey()).isEqualTo(shortUrlKey);
        assertThat(save.getReqCount()).isEqualTo(reqCount);
    }

    @DisplayName("단축 URL 엔티티 요청 수 증가 성공 ")
    @Test
    public void SHORT_URL_REQ_COUNT_UPDATE_SUCCESS() {
        //given
        String domain = "https://www.musinsa.com";
        String pathParm = "/?m=magazine&tvMode=on&uid=16027";
        String shortUrlKey = "/cdG1";
        long reqCount = 1L;

        //given
        ShortUrl shortUrl = ShortUrl.builder()
                .originUrl(domain + pathParm)
                .reqCount(reqCount)
                .url(domain + shortUrlKey)
                .key(shortUrlKey)
                .build();

        //when
        ShortUrl save = shortUrlRepository.save(shortUrl);
        save.addReqCount();

        //then
        assertThat(save.getUrl()).isEqualTo(domain + shortUrlKey);
        assertThat(save.getOriginUrl()).isEqualTo(domain + pathParm);
        assertThat(save.getKey()).isEqualTo(shortUrlKey);
        assertThat(save.getReqCount()).isEqualTo(reqCount + 1);
    }

    @DisplayName("단축 URL 엔티티 요청 수정 성공 ")
    @Test
    public void SHORT_URL_UPDATE_SUCCESS() {

        //given
        String domain = "https://www.musinsa.com";
        String pathParm = "/?m=magazine&tvMode=on&uid=16027";
        String shortUrlKey = "/cdG1";
        long reqCount = 1L;

        //given
        ShortUrl shortUrl = ShortUrl.builder()
                .originUrl(domain + pathParm)
                .reqCount(reqCount)
                .build();

        //when
        ShortUrl save = shortUrlRepository.save(shortUrl);
        save.saveDomainAndKey(domain, shortUrlKey);

        //then
        assertThat(save.getUrl()).isEqualTo(domain + shortUrlKey);
        assertThat(save.getOriginUrl()).isEqualTo(domain + pathParm);
        assertThat(save.getKey()).isEqualTo(shortUrlKey);
        assertThat(save.getReqCount()).isEqualTo(reqCount);
    }


    @DisplayName("URL 키 기준 조회 성공 ")
    @Test
    public void ORIGIN_URL_FIND_BY_KEY_SUCCESS() {
        //given
        String domain = "https://www.musinsa.com";
        String pathParm = "/?m=magazine&tvMode=on&uid=16027";
        String shortUrlKey = "/cdG1";

        //when
        SHORT_URL_SAVE_SUCCESS();
        ShortUrl save = shortUrlRepository.findOriginUrlByKey(shortUrlKey)
                .orElseThrow(() -> new CommonException(CommonError.SERVICE_ERROR));

        //then
        assertThat(save.getUrl()).isEqualTo(domain + shortUrlKey);
        assertThat(save.getOriginUrl()).isEqualTo(domain + pathParm);
        assertThat(save.getKey()).isEqualTo(shortUrlKey);
    }
}
