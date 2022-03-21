package com.musinsa.encoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShortUrlEncoderTest {

    @DisplayName("단축 URL 생성 테스트 성공")
    @Test
    public void SHORT_URL_MAKE_TEST_SUCCESS() {
        //given
        Base62Encoder encoder = new ShortUrlEncoder();
        String encode = encoder.encode(Integer.MAX_VALUE);

        //when
        int decode = encoder.decode(encode.substring(1));

        //then
        assertNotNull(encode);
        assertEquals(decode, Integer.MAX_VALUE);
    }
}
