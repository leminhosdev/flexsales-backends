package com.example.flexsaless.service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import com.example.flexsaless.Service.ExcelFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExcelFileServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private StorageFileRepository storageFileRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ExcelFileService excelFileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadFile() throws IOException {

        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);
        when(auth.isAuthenticated()).thenReturn(true);


        Client client = new Client();
        when(auth.getPrincipal()).thenReturn(Optional.of(client));


        MockMultipartFile file = new MockMultipartFile(
                "test.xlsx",
                "test.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                new byte[0]
        );

        excelFileService.uploadFile(file);


    }

    @Test
    public void testDelete() {

    }
}
