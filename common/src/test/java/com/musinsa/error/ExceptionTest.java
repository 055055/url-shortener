package com.musinsa.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {

    @DisplayName("UnsupportedOperationException Message 테스트 성공")
    @Test
    public void EXCEPTION_MESSAGE_TEST_SUCCESS(){
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @DisplayName("CommonException CommonError.SERVICE_ERROR 테스트 성공")
    @Test
    public void SERVICE_EXCEPTION_MESSAGE_TEST_SUCCESS(){
        CommonException exception = assertThrows(CommonException.class, () -> {
            throw new CommonException(CommonError.SERVICE_ERROR);
        });
        assertEquals(exception.getCommonError().getMessage(), CommonError.SERVICE_ERROR.getMessage());
    }

}