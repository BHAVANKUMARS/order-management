package com.order.management.service.product;

import com.order.management.exception.BadRequestException;
import com.order.management.model.Product;
import com.order.management.repository.ProductRepository;
import com.order.management.request.ProductRequest;
import com.order.management.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements CrudService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String PRODUCT_NOT_FOUND = "Product Not Found";
    @Autowired
    private ProductRepository productRepository;

    public void validateProductRequest(ProductRequest productRequest){

        LOGGER.info("Product Request Validation started");

         if(productRequest.getProductName() == null || productRequest.getProductName().isEmpty())
             throw new BadRequestException("Product Name should not be empty");

         if(productRequest.getIsActive() == null)
             throw new BadRequestException("Product status should not be null");

         if(productRequest.getPrice() == null)
             throw new BadRequestException("Product Price should not be null");

         if(productRequest.getDescription() == null || productRequest.getDescription().isEmpty())
             throw new BadRequestException("Product Description should not be null or empty");

        LOGGER.info("Product Request Validation completed");

    }

    @Override
    public void save(Object data) {

        Product product = new Product((ProductRequest)data);

        Optional<Product> productDetail = productRepository.findByProductName(product.getProductName());

        if(productDetail.isPresent())
            throw new BadRequestException("Product Already Exist");
        else {

            productRepository.save(product);

            LOGGER.info("Product Inserted successfully");
        }

    }

    @Override
    public void delete(String data) {

        Optional<Product> productDetail = productRepository.findByProductName(data);

        if(productDetail.isPresent())
            productRepository.deleteById(productDetail.get().getProductId());
        else
            throw new BadRequestException(PRODUCT_NOT_FOUND);
    }

    @Override
    public void update(Object data) {

        ProductRequest product = (ProductRequest) data;

        Optional<Product> productDetail = productRepository.findByProductName(product.getProductName());

        if (productDetail.isPresent()){

            if(product.getIsActive() != null)
                productDetail.get().setActive(product.getIsActive());
            if(product.getPrice() != null)
                productDetail.get().setPrice(product.getPrice());
            if(product.getDescription() != null || !product.getDescription().isEmpty())
                productDetail.get().setDescription(product.getDescription());

            productRepository.save(productDetail.get());

        }else
            throw new BadRequestException(PRODUCT_NOT_FOUND);

    }

    @Override
    public List<Product> listAllProduct(){

        return productRepository.findAll();

    }
}
