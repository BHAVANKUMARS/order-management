package com.order.management.model;

import com.order.management.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
    @SequenceGenerator(name = "product_seq",allocationSize = 1,sequenceName = "product_seq_id")
    private Long productId;

    private String productName;

    private boolean isActive;

    private Double price;

    private String description ;

    private Date createdAt;

    private Date updatedAt;


    @PrePersist // before save the entity in db, it set the field value in first time
    public void setDate(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate // // before save the entity in db, it set the field value when modify / update the entity
    public void updateDate(){
        this.updatedAt = new Date();
    }


    public Product(ProductRequest productRequest){

        this.productName = productRequest.getProductName();

        this.isActive = productRequest.getIsActive();

    }

}
