package com.order.management.controller;

import com.order.management.exception.BadRequestException;
import com.order.management.service.user.UserService;
import com.order.management.utilis.CommonFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.order.management.request.user.UserRequest;

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String RECORD_INSERTED_SUCCESSFULLY = "Record Inserted Successfully";
    private final String RECORD_DELETED_SUCCESSFULLY = "Record Deleted Successfully";

    private final String RECORD_UPDATED_SUCCESSFULLY = "Record Updated Successfully";

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunction commonFunction;

    @PostMapping(value = "/addUser")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){

        LOGGER.info("Add User Controller");

        userService.validateUserRequest(userRequest);

        userService.save(userRequest);

        return commonFunction.baseResponse(RECORD_INSERTED_SUCCESSFULLY);

    }

    @PutMapping("/editUser")
    public ResponseEntity editUser(@RequestBody UserRequest userRequest){

        LOGGER.info("Edit User Details");

        userService.validateUserEditRequest(userRequest);

        userService.update(userRequest);

        return commonFunction.baseResponse(RECORD_UPDATED_SUCCESSFULLY);
    }

    @DeleteMapping("/deleteUser/{mobileNumber}")
    public ResponseEntity deleteUser(@PathVariable (name = "mobileNumber",required = false) String mobileNumber){

        LOGGER.info("mobileNumber "+mobileNumber);

        if(mobileNumber == null || mobileNumber.isEmpty())
            throw new BadRequestException("UserName should not be empty");

        userService.delete(mobileNumber);

        return commonFunction.baseResponse(RECORD_DELETED_SUCCESSFULLY);

    }
}
