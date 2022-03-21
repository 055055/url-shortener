package com.musinsa.url.web.controller;

import com.musinsa.url.service.ShortUrlService;
import com.musinsa.url.web.dto.ShortUrlReqDto;
import com.musinsa.url.web.dto.ShortUrlResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/v1/short-url")
@Controller
@RequiredArgsConstructor
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @GetMapping
    public String shortUrlForm(){
        return "short-url-form.html";
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> getOrCreateShortUrl(@RequestBody @Valid ShortUrlReqDto req){
        return new ResponseEntity<>(shortUrlService.getOrCreateShortUrl(req), HttpStatus.CREATED);
    }

    @GetMapping("/table")
    public String shortUrlTable(){
        return "short-url-table.html";
    }

    @GetMapping("/")
    public @ResponseBody Map<String, List> getAllShortUrl(){
        List<ShortUrlResDto> allShortUrl = shortUrlService.getAllShortUrl();
        Map<String, List> data = new HashMap<>();
        data.put("data",allShortUrl);
        return data;
    }
}
