package com.musinsa.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonError {
    SHORT_URL_LENGTH_EXCEEDED("1000", "단축 URL 허용 길이 초과", HttpStatus.INTERNAL_SERVER_ERROR),
    REQUEST_VALIDATION("4001", "요청값 벨리데이션 체크", HttpStatus.BAD_REQUEST),
    PAGE_NOT_FOUND("4002", "잘못된 페이지 입니다.", HttpStatus.NOT_FOUND),
    SERVICE_ERROR("5000", "내부 서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private String code;
    private String message;
    private HttpStatus httpStatus;

    CommonError(String resultCode, String resultMessage, HttpStatus httpStatus) {
        this.code = resultCode;
        this.message = resultMessage;
        this.httpStatus = httpStatus;
    }

    public ResultError getResultError() {
        return ResultError.builder()
                .code(this.code)
                .message(this.message)
                .httpStatus(this.httpStatus)
                .build();
    }
}
