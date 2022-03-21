package com.musinsa.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class ResultError {
    private String code;
    private String message;
    private HttpStatus httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldValue> fieldValues;

    @Getter
    @Builder
    public static class FieldValue {
        private String field;
        private Object value;
        private String reason;
    }
}
