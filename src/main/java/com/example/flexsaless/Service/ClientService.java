package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public Client getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            return client;
        }
        return null;
    }

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client save(Client client){
        return  this.clientRepository.save(client);
    }

    public Client uploadFile(){
        Client clientLogged = getLoggedUser();

         return this.clientRepository.save(clientLogged);
    }
}
