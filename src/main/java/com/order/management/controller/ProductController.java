package com.order.management.controller;

import com.order.management.exception.BadRequestException;
import com.order.management.request.ProductRequest;
import com.order.management.service.product.ProductService;
import com.order.management.utilis.CommonFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String PRODUCT_INSERTED_SUCCESSFULLY = "Product Inserted Successfully";

    private final String PRODUCT_DELETED_SUCCESSFULLY = "Product Deleted Successfully";

    private final String PRODUCT_STATUS_UPDATED_SUCCESSFULLY = "Product Status Updated Successfully";

    @Autowired
    private ProductService productService;

    @Autowired
    private CommonFunction commonFunction;

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest){

        LOGGER.info("Inside Product Controller");

        productService.validateProductRequest(productRequest);

        productService.save(productRequest);

        return commonFunction.baseResponse(PRODUCT_INSERTED_SUCCESSFULLY);

    }

    @PutMapping("/editProductStatus")
    public ResponseEntity editProductStatus(@RequestBody ProductRequest productRequest){

        productService.update(productRequest);

        return commonFunction.baseResponse(PRODUCT_STATUS_UPDATED_SUCCESSFULLY);
    }

    @GetMapping("/listAllProduct")
    public ResponseEntity listAllProduct(){

        return commonFunction.baseResponse(productService.listAllProduct());

    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteProduct(@RequestParam (name = "productName",required = false) String productName){

        LOGGER.info("product "+productName);

        if(productName == null || productName.isEmpty())
            throw new BadRequestException("Product Name should not be null or empty");

        productService.delete(productName);

        return commonFunction.baseResponse(PRODUCT_DELETED_SUCCESSFULLY);

    }
}
