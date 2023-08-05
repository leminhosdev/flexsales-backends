package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    private StorageFileRepository storageFileRepository;
    @Autowired
    private ProductRepository productRepository;

    public Client getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            return client;
        }
        return null;
    }

    public ClientService(ClientRepository clientRepository, StorageFileRepository storageFileRepository) {
        this.clientRepository = clientRepository;
        this.storageFileRepository = storageFileRepository;
    }


    public Client save(Client client) {
        return this.clientRepository.save(client);
    }


}
