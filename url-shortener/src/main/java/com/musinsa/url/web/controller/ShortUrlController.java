package com.musinsa.url.web.controller;

import com.musinsa.url.service.ShortUrlService;
import com.musinsa.url.web.dto.ShortUrlReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;


@Slf4j
@RequestMapping("/v1/short-url")
@RestController
@RequiredArgsConstructor
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<?> getOrCreateShortUrl(@RequestBody @Valid ShortUrlReqDto req) {
        return new ResponseEntity<>(shortUrlService.getOrCreateShortUrl(req), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public Map<String, List> getAllShortUrl() {
        return singletonMap("data", shortUrlService.getAllShortUrl());
    }
}
