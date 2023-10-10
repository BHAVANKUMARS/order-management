package com.order.management.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userName;

    private String mobileNumber;

    private String email;

    private String password;

}
