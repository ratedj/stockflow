package com.cuscatlan.inventory_service.services;

import com.cuscatlan.inventory_service.data.ProductsRepository;
import com.cuscatlan.inventory_service.dto.ProductResponse;
import com.cuscatlan.inventory_service.models.Product;
import com.cuscatlan.inventory_service.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements   IProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> GetAll()
    {
        List<Product> productResponseList = new ArrayList<>();

            productResponseList=productsRepository.findAll();

            List<ProductResponse> productResponseList2 = new ArrayList<>();
            for(Product product:productResponseList)    {
             productResponseList2.add(productMapper.toDto(product));
            }
            return productResponseList2;

    }

    @Override
    public ProductResponse GetById(int id) {

        Product product = productsRepository.findById(id);

        ProductResponse productResponse = productMapper.toDto(product);

        return productResponse;
    }
}
