package com.example.flexsaless.Repositoriy;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Util.ClientCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void save_PersistClient_WhenSucessful(){
        Client clientTobeSaved = ClientCreator.createClientToBeSaved();
        Client clientSaved =  this.clientRepository.save(clientTobeSaved);
        Assertions.assertNotNull(clientSaved);
        Assertions.assertNotNull(clientSaved.getId());
        Assertions.assertEquals(clientSaved, clientTobeSaved);
    }
    @Test
    void update_UpdateClient_WhenSucessful(){
        Client clientTobeSaved = ClientCreator.createClientToBeSaved();
        Client clientSaved =  this.clientRepository.save(clientTobeSaved);
        clientSaved.setClientName("ududsalah");
        Client clientUpdated =  this.clientRepository.save(clientSaved);

        Assertions.assertNotNull(clientUpdated);
        Assertions.assertNotNull(clientUpdated.getId());
        Assertions.assertEquals(clientSaved, clientTobeSaved);
    }
    @Test
    void delete_DeleteClient_WhenSucessful(){
        Client clientTobeSaved = ClientCreator.createClientToBeSaved();
        Client clientSaved =  this.clientRepository.save(clientTobeSaved);
        this.clientRepository.delete(clientSaved);
        Optional<Client> clientOptional = this.clientRepository.findById(clientSaved.getId());
        Assertions.assertTrue(clientOptional.isEmpty());
    }




}
