package com.example.flexsaless.service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import com.example.flexsaless.Service.ClientService;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

@Builder
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    StorageFileRepository storageFileRepository;
    Client client;

    @BeforeEach
    public void setUp(){
        client = Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("borda324vs").build();
    }

    @Test
    void ShouldSaveClientInDataBase(){
        when(clientRepository.save(client)).thenReturn(client);
        Client clientSaved = clientService.save(client);

        Assertions.assertEquals(client, clientSaved);
        verify(clientRepository).save(client);
        verifyNoMoreInteractions(clientRepository);
    }
}
