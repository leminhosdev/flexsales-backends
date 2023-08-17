package com.example.flexsaless.Controller;

import com.example.flexsaless.Service.ExcelFileService;
import com.example.flexsaless.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductControlller productControlller;
    @Mock
    private ProductService productService;
    @Mock
    private ExcelFileService excelFileService;

    @Test
    void delete_ShouldDeleteWhenSucessfull(){

    }
}
