package com.musinsa.encoder;

import com.musinsa.error.CommonError;
import com.musinsa.error.CommonException;
import lombok.extern.slf4j.Slf4j;

import static com.musinsa.encoder.Base62Encoder.BASE62_CHARACTERS;

@Slf4j
public class ShortUrlEncoder extends Base62Encoder {

    @Override
    public String encode(int seq) {
        StringBuilder sb = new StringBuilder();
        while (seq > 0) {
            sb.append(BASE62_CHARACTERS.charAt((seq % BASE62_CHARACTERS.length())));
            seq /= BASE62_CHARACTERS.length();
        }
        log.debug("short url suffix {}", sb);
        if (sb.length() > 8) throw new CommonException(CommonError.SHORT_URL_LENGTH_EXCEEDED);

        sb.insert(0, "/");
        return sb.toString();
    }

    @Override
    public int decode(String shortUrl) {
        int decodeNum = 0;
        int multipleNum = 1;
        log.debug("short url {}", shortUrl);

        for (int i = 0; i < shortUrl.length(); i++) {
            int num = BASE62_CHARACTERS.indexOf(shortUrl.charAt(i));
            decodeNum += num * multipleNum;
            multipleNum *= BASE62_CHARACTERS.length();
        }
        log.debug("decode num : {}", decodeNum);

        return decodeNum;
    }
}
