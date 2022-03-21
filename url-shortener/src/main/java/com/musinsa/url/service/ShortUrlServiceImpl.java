package com.musinsa.url.service;

import com.musinsa.encoder.Base62Encoder;
import com.musinsa.encoder.ShortUrlEncoder;
import com.musinsa.error.CommonError;
import com.musinsa.error.CommonException;
import com.musinsa.url.repository.ShortUrl;
import com.musinsa.url.repository.ShortUrlRepository;
import com.musinsa.url.web.dto.ShortUrlReqDto;
import com.musinsa.url.web.dto.ShortUrlResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;

    @Override
    @Transactional
    public ShortUrlResDto getOrCreateShortUrl(ShortUrlReqDto req) {
        ShortUrl result = shortUrlRepository.findByOriginUrl(req.getOriginUrl());
        if (result != null) {
            result.addReqCount();
            return new ShortUrlResDto(result.getUrl(), result.getOriginUrl(), result.getReqCount());
        } else {
            Base62Encoder encoder = new ShortUrlEncoder();
            ShortUrl shortUrl = shortUrlRepository.save(req.createShortUrl());
            String shortKey = encoder.encode((shortUrl.getSeq().intValue() + 100));
            shortUrl.updateShortUrl(req.getDomain(), shortKey);
            return new ShortUrlResDto(shortUrl.getUrl(), shortUrl.getOriginUrl(), shortUrl.getReqCount());
        }
    }

    @Override
    public String getOriginUrl(String key) {
        log.debug(key);
        ShortUrl result = shortUrlRepository.findOriginUrlByKey(key).orElseThrow(()
                -> new CommonException(CommonError.PAGE_NOT_FOUND));
        return result.getOriginUrl();
    }

    @Override
    public List<ShortUrlResDto> getAllShortUrls() {
        return shortUrlRepository.findAll()
                .stream()
                .map(shortUrl ->
                        new ShortUrlResDto(
                                shortUrl.getUrl(),
                                shortUrl.getOriginUrl(),
                                shortUrl.getReqCount())
                ).collect(Collectors.toList());
    }
}
