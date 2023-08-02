package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import com.example.flexsaless.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductControlller {
    @Autowired
    private ProductService productService;
    @GetMapping("/searchProduct")
    public List<Product> SearchProductsByName(@RequestParam String keyWord){
       return productService.searchProductsByName(keyWord);
    }
}
