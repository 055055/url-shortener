package com.musinsa.encoder;

import com.musinsa.error.CommonError;
import com.musinsa.error.CommonException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShortUrlEncoder extends Base62Encoder{


    @Override
    public String encode(int seq) throws CommonException{
        StringBuilder sb = new StringBuilder();
        try{
            while(seq > 0) {
                sb.append(BASE62_CHARACTERS.charAt((seq % BASE62_CHARACTERS.length())));
                seq /= BASE62_CHARACTERS.length();
            }
            log.debug("short url suffix : {}",sb.toString());
            if(sb.length()>8) throw new CommonException(CommonError.SHORT_URL_LENGTH_EXCEEDED);

            sb.insert(0,"/");
        }catch (CommonException ce){
            log.error("Exception : {}",ce);
            throw new CommonException(ce.getCommonError());
        }catch (Exception e){
            log.error("Exception : {}",e);
            throw new CommonException(CommonError.SERVICE_ERROR);
        }

        return sb.toString();
    }


    @Override
    public int decode(String shortUrl) throws CommonException {
        int decodeNum = 0;
        int multipleNum = 1;
        log.debug("short url : {}",shortUrl);

       try {
            for (int i = 0; i < shortUrl.length(); i++) {
                int num = BASE62_CHARACTERS.indexOf(shortUrl.charAt(i));
                decodeNum += num * multipleNum;
                multipleNum *= BASE62_CHARACTERS.length();
            }
           log.debug("decode num : {}",decodeNum);

           return decodeNum;
       }catch (Exception e){
           log.error("Exception : {}",e);
           throw new CommonException(CommonError.SERVICE_ERROR);
       }
    }


}


