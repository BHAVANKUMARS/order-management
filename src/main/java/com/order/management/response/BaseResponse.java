package com.order.management.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private int code;

    private String status;

    private T data;

    public BaseResponse(){
        this.code = 200;
        this.status = "success";
    }


}
