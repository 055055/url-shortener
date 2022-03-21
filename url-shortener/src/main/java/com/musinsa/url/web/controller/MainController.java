package com.musinsa.url.web.controller;

import com.musinsa.url.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ShortUrlService shortUrlService;

    @GetMapping("/{shortUrl}")
    public RedirectView shortUrlRedirect(HttpServletRequest req) {
        return new RedirectView(shortUrlService.findOriginUrlByShortUrlKey(req.getRequestURI()));
    }
}
