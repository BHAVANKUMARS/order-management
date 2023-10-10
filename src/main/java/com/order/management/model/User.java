package com.order.management.model;

import com.order.management.request.user.UserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @SequenceGenerator(name = "user_seq",allocationSize = 1,sequenceName = "user_seq_id")
    @Column(name = "user_id")
    private Long userId;

    private String userName;

    private String mobileNumber;

    private String email;

    private String password;

    private Date createdAt;

    private Date updatedAt;

//    @Column(name = "is_super_admin")
//    private boolean isSuperAdmin;

    @PrePersist // before save the entity in db, it set the field value in first time
    public void setDate(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate // // before save the entity in db, it set the field value when modify / update the entity
    public void updateDate(){
        this.updatedAt = new Date();
    }


    public User(UserRequest userRequest){

        this.userName = userRequest.getUserName();
        this.mobileNumber = userRequest.getMobileNumber();
        this.email   = userRequest.getEmail();
        this.password = userRequest.getPassword();


    }

}
