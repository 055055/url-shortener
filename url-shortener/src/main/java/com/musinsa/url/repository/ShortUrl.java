package com.musinsa.url.repository;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShortUrl extends BaseEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private String url;

    @Column(unique = true, length = 8)
    private String key;

    @Column(nullable = false)
    private String originUrl;

    @Column(nullable = false)
    private Long reqCount;

    @Builder
    public ShortUrl(String url, String key, String originUrl, Long reqCount) {
        this.url = url;
        this.key = key;
        this.originUrl = originUrl;
        this.reqCount = reqCount;
    }

    public void addReqCount(){
        this.reqCount +=1;
    }

    public void updateShortUrl(String domain, String key){
        this.url = domain+key;
        this.key = key;
        addReqCount();
    }
}

