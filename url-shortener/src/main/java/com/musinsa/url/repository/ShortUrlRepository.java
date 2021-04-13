package com.musinsa.url.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    ShortUrl findByOriginUrl(String originUrl);

    Optional<ShortUrl> findOriginUrlByKey(String key);

}
