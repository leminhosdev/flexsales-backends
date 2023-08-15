package com.example.flexsaless.service;

import com.example.flexsaless.Controller.ClientControllerTest;
import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import com.example.flexsaless.Security.TokenService;
import com.example.flexsaless.Service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @InjectMocks
    ClientService clientService;
    @InjectMocks
    private ClientControllerTest clientController;
    @Mock
    ClientRepository clientRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    StorageFileRepository storageFileRepository;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;



    Client client;

    @BeforeEach
    public void setUp(){
        client = Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("cachorro80").build();

    }

    @Test
   public void ShouldSaveClientInDataBase(){

        when(clientRepository.save(client)).thenReturn(client);

        Client clientSaved = clientService.save(client);

        assertEquals(client, clientSaved);
        verify(clientRepository).save(client);
        verifyNoMoreInteractions(clientRepository);
    }

}
