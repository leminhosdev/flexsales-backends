package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ClientControllerTest {
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void save_ReturnClientSaved_WhenSucessfull(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();
        Client clientSaved = this.clientService.save(clientToBeSaved);
        Assertions.assertEquals(clientSaved, clientToBeSaved);
        Assertions.assertNotNull(clientSaved.getId());
    }
}
