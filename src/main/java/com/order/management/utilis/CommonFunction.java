package com.order.management.utilis;

import com.order.management.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommonFunction<T> {

    public ResponseEntity baseResponse(T data){

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setData(data);

        return ResponseEntity.ok(baseResponse);

    }

}
