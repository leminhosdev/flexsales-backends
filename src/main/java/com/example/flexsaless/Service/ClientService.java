package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    private StorageFileRepository storageFileRepository;

    public Client getLoggedUser(){
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


    public Client save(Client client){
        return  this.clientRepository.save(client);
    }

    public ExcelFile uploadFile(MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();

            String filename = file.getOriginalFilename();
            ExcelFile excelFile = new ExcelFile(filename, file.getContentType(), file.getBytes(), client);
           client.setExcelFile(excelFile);
            ExcelFile savedFile = this.storageFileRepository.save(excelFile);
            this.clientRepository.save(client);
            return savedFile;
        }
        return null;
    }
}
