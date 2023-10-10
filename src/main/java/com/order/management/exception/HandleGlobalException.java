package com.order.management.exception;

import com.order.management.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandleGlobalException {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(BadRequestException badRequestException){

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setCode(HttpStatus.BAD_REQUEST.value());

        baseResponse.setStatus("Failure");

        Map<String,String> message = new HashMap<>();

        message.put("message",badRequestException.getMessage());

        baseResponse.setData(message);

        return new ResponseEntity(baseResponse,HttpStatus.BAD_REQUEST);

    }
}
