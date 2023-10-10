package com.order.management.service;

import com.order.management.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface CrudService<T> {

    void save(T data);

    void delete(String data);

    void update(T data);

    default List<Product> listAllProduct(){
        return new ArrayList<>();
    }


}
