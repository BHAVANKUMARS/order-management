package com.order.management.service;

import com.order.management.exception.BadRequestException;
import com.order.management.repository.UserRepository;
import com.order.management.request.LoginRequest;
import com.order.management.utilis.CommonFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.order.management.model.User;

@Service
public class LoginService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String USER_NOT_EXIST = "User Not Exist";
    private final String INCORRECT_PASSWORD = "Incorrect Password";
    private final String LOGIN_SUCCESSFULLY= "Login Successfully";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonFunction commonFunction;

    public void validateLoginParams(LoginRequest loginRequest){

        LOGGER.info("Login Request Validation Started");

        if(loginRequest.getUserName() == null || loginRequest.getUserName().isEmpty())
            throw new BadRequestException("UserName should not be null or empty");

        if(loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty())
            throw new BadRequestException("Password should not be null or empty");

        LOGGER.info("Login Request Validation Completed");

    }

    public ResponseEntity authenticateUser(LoginRequest loginRequest){

        Optional<User> userDetail = userRepository.findByMobileNumber(loginRequest.getUserName());

        if (userDetail.isPresent()){

            if (userDetail.get().getPassword().equals(loginRequest.getPassword()))
                return commonFunction.baseResponse(LOGIN_SUCCESSFULLY);
            else
                throw new BadRequestException(INCORRECT_PASSWORD);

        }else
            throw new BadRequestException(USER_NOT_EXIST);

    }


}
