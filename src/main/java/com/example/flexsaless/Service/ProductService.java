package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private StorageFileRepository storageFileRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProductsByName(String keyWord){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            List<Product> list = productRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseAndClientOwnerId(
                    keyWord, keyWord, client.getId()
            );
            List<Product> filteredList = list.stream()
                    .filter(product -> product.getClientOwner().getId() == client.getId())
                    .collect(Collectors.toList());
            return filteredList;
        }
        return null;
    }




}
