package com.musinsa.error;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private CommonError commonError;

    public CommonException() {
        super();
    }

    public CommonException(CommonError commonError) {
        super(new CommonException());
        this.commonError = commonError;
    }

    public CommonException(Throwable cause, CommonError commonError) {
        super(cause);
        this.commonError = commonError;
    }
}
