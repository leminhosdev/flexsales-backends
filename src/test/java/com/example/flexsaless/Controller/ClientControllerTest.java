package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import com.example.flexsaless.Security.TokenService;
import com.example.flexsaless.Service.ClientService;
import com.example.flexsaless.Service.ExcelFileService;
import com.example.flexsaless.Util.ClientCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ClientControllerTest {
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private ClientController clientController;

    @Mock
    private TokenService tokenService;
    @Mock
    private ClientService clientService;
    @Mock
    private ExcelFileService excelFileService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private StorageFileRepository storageFileRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        clientController = new ClientController( clientService, bCryptPasswordEncoder);
        // Other mock setup
    }
    @Test
    void save_ReturnClientSaved_WhenSucessfull(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();
        Client clientSaved = ClientCreator.createValidClient();
        Mockito.when(clientService.save(Mockito.any(Client.class))).thenReturn(clientSaved);
        ResponseEntity<Client> responseEntity = clientController.save(clientToBeSaved);
        Client savedClient = responseEntity.getBody();

        Assertions.assertNotNull(savedClient);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(clientSaved.getEmail(), savedClient.getEmail());
    }
}
