package com.musinsa.error;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private CommonError commonError;

    public CommonException(){super();}

    public CommonException(CommonError commonError) {
        super(new CommonException());
        this.commonError = commonError;
    }

    public CommonException(String message, CommonError commonError) {
        super(message);
        this.commonError = commonError;
    }

    public CommonException(String message, Throwable cause, CommonError commonError) {
        super(message, cause);
        this.commonError = commonError;
    }

    public CommonException(Throwable cause, CommonError commonError) {
        super(cause);
        this.commonError = commonError;
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CommonError commonError) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.commonError = commonError;
    }
}
