package com.order.management.controller;

import com.order.management.response.BaseResponse;
import com.order.management.service.LoginService;
import com.order.management.utilis.CommonFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.order.management.request.LoginRequest;

@RestController
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private CommonFunction commonFunction;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody LoginRequest loginRequest){

        LOGGER.info("Login Controller");

        LOGGER.info("Login Request "+loginRequest.toString());

        //validate the user request
        loginService.validateLoginParams(loginRequest);

        return loginService.authenticateUser(loginRequest);

    }
}
