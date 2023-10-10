package com.order.management.service.user;

import com.order.management.exception.BadRequestException;
import com.order.management.model.User;
import com.order.management.repository.UserRepository;
import com.order.management.request.user.UserRequest;
import com.order.management.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements CrudService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String USER_NOT_EXIT = "User Not Exist";

    @Autowired
    private UserRepository userRepository;

    public void validateUserRequest(UserRequest userRequest){

        LOGGER.info("Add User Request Validation started");

        if(userRequest.getUserName() == null || userRequest.getUserName().isEmpty())
            throw new BadRequestException("UserName should not be null or empty");

        if(userRequest.getMobileNumber() == null || userRequest.getMobileNumber().isEmpty())
            throw new BadRequestException("Mobile Number should not be null or empty");
        else if(userRequest.getMobileNumber().length()!=10)
            throw new BadRequestException("Mobile Number should be 10 digits only");

        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty())
            throw new BadRequestException("Email should not be null or empty");

        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty())
            throw new BadRequestException("Password should not be empty or null");
        else if(userRequest.getPassword().length()<=8)
            throw new BadRequestException("Password length should be greater than 8");

        LOGGER.info("Add User Request Validation Completed");

    }

    public void validateUserEditRequest(UserRequest userRequest){

        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty())
            throw new BadRequestException("Email should not be null or empty");

        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty())
            throw new BadRequestException("Password should not be empty or null");
        else if(userRequest.getPassword().length()<=8)
            throw new BadRequestException("Password length should be greater than 8");


    }

    @Override
    public void save(Object userRequest){

        User user = new User((UserRequest) userRequest);

        Optional<User> userDetail = userRepository.findByMobileNumber(user.getMobileNumber());

        if(userDetail.isPresent())
            throw new BadRequestException("User Already Exist");
        else {

            userRepository.save(user);

            LOGGER.info("User Detail successfully saved " + (user.toString()));
        }

    }

    @Override
    public void delete(String data) {

        Optional<User> userDetail = userRepository.findByMobileNumber(data);

        if (userDetail.isPresent())
            userRepository.deleteById(userDetail.get().getUserId());
        else
            throw new BadRequestException(USER_NOT_EXIT);


    }

    @Override
    public void update(Object data) {

        UserRequest user = (UserRequest) data;

        Optional<User> userDetail = userRepository.findByMobileNumber(user.getMobileNumber());

        if(userDetail.isPresent()){

            if(user.getUserName() != null && !user.getUserName().isEmpty())
                userDetail.get().setUserName(user.getUserName());

            if(user.getEmail() != null && !user.getEmail().isEmpty())
                userDetail.get().setEmail(user.getEmail());

            userDetail.get().setPassword(user.getPassword());

            userRepository.save(userDetail.get());

        }else
            throw new BadRequestException(USER_NOT_EXIT);

    }


}
