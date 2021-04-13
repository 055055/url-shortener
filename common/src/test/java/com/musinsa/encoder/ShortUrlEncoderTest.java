package com.musinsa.encoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortUrlEncoderTest {


    @DisplayName("단축 URL 생성 테스트 성공")
    @Test
    public void SHORT_URL_MAKE_TEST_SUCCESS(){

        Base62Encoder encoder = new ShortUrlEncoder();
        System.out.println("encodeNum = " + Integer.MAX_VALUE);

        String encode = encoder.encode( Integer.MAX_VALUE);
        System.out.println("encode = " + encode);

        int decode = encoder.decode(encode);
        System.out.println("decode = " + decode);

        assertNotNull(encode);
        assertEquals(decode, Integer.MAX_VALUE);

    }




}