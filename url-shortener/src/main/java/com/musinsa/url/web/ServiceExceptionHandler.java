package com.musinsa.url.web;

import com.musinsa.error.CommonError;
import com.musinsa.error.CommonException;
import com.musinsa.error.ResultError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ServiceExceptionHandler {


    /**
     * Common Exception Handler
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> commonExceptionHandler(CommonException e){
       return new ResponseEntity<>(e.getCommonError().getResultError(), e.getCommonError().getResultError().getHttpStatus());
    }

    /**
     * RequestBody Validation Exception Handler
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> httpMsgConverterValidationHanlder(MethodArgumentNotValidException e){
        return responseValidException(e.getBindingResult());
    }

    /**
     * Parameter Validation Exception Handler
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> modelAttributeValidationHanlder(BindException e){
        return responseValidException(e.getBindingResult());
    }



    private ResponseEntity<?> responseValidException(BindingResult bindingResult) {
        List<ResultError.FieldValue> fieldValues = new ArrayList<>();
        for (FieldError fieldError :bindingResult.getFieldErrors()){
            ResultError.FieldValue fieldValue = ResultError.FieldValue
                                                            .builder()
                                                            .field(fieldError.getField())
                                                            .value(fieldError.getRejectedValue())
                                                            .reason(fieldError.getDefaultMessage())
                                                             .build();
            fieldValues.add(fieldValue);
        }

        ResultError response = ResultError.builder()
                                          .code(CommonError.REQUEST_VALIDATION.getCode())
                                          .message(CommonError.REQUEST_VALIDATION.getMessage())
                                          .httpStatus(CommonError.REQUEST_VALIDATION.getHttpStatus())
                                          .fieldValues(fieldValues)
                                          .build();
        return new ResponseEntity(response, response.getHttpStatus());
    }


}
