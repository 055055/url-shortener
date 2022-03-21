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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;

    @Override
    @Transactional
    public ShortUrlResDto getOrCreateShortUrl(ShortUrlReqDto req) throws CommonException {
        try{
            ShortUrl result = shortUrlRepository.findByOriginUrl(req.getOriginUrl());
            if(result != null){
                result.addReqCount();
                return new ShortUrlResDto(result.getUrl(), result.getOriginUrl(), result.getReqCount());
            }else{
                ShortUrl save = shortUrlRepository.save(req.createShortUrl());
                Base62Encoder encoder = new ShortUrlEncoder();
                String shortKey = encoder.encode((save.getSeq().intValue()+100));
                save.updateShortUrl(req.getDomain(), shortKey);
                return new ShortUrlResDto(save.getUrl(), save.getOriginUrl(), save.getReqCount());
            }
        }catch (CommonException ce){
            log.info("CommonException : {}",ce.getCommonError());
            throw new CommonException(ce.getCommonError());
        }catch (Exception e){
            log.info("Exception : {}",e.getMessage(),e);
            throw new CommonException(e, CommonError.SERVICE_ERROR);
        }
    }

    @Override
    public String findOriginUrlByShortUrlKey(String key) throws CommonException {
        try{
            log.debug("key : {}"+key);
            ShortUrl result = shortUrlRepository.findOriginUrlByKey(key).orElseThrow(()
                                                        -> new CommonException(CommonError.PAGE_NOT_FOUND));
            return result.getOriginUrl();
        }catch (CommonException ce){
            log.info("CommonException : {}",ce.getCommonError());
            throw new CommonException(ce.getCommonError());
        }catch (Exception e){
            log.info("Exception : {}",e.getMessage(),e);
            throw new CommonException(e, CommonError.SERVICE_ERROR);
        }
    }

    @Override
    public List<ShortUrlResDto> getAllShortUrl() throws CommonException {
        ArrayList result = new ArrayList();
        try {
            List<ShortUrl> shortUrls = shortUrlRepository.findAll();
            for (ShortUrl shortUrl: shortUrls )  {
                result.add(new ShortUrlResDto(shortUrl.getUrl(), shortUrl.getOriginUrl(), shortUrl.getReqCount()));
            }
           return result;
        }catch (Exception e){
           log.info("Exception : {}",e.getMessage(),e);
           throw new CommonException(e, CommonError.SERVICE_ERROR);
        }
    }
}
