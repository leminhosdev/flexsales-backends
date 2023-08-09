package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.OrderEntity;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Security.TokenService;
import com.example.flexsaless.Service.ClientService;
import com.example.flexsaless.Service.ExcelFileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class ClientController {
    @Autowired
    private TokenService tokenService;
    private ClientService clientService;
    @Autowired
    private ExcelFileService excelFileService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Client> save(@Valid @RequestBody Client client){
        String encodedPassword = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        this.clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PostMapping("/login")
    public String login(@RequestBody Client client){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(client.getEmail(), client.getPassword());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Client) authenticate.getPrincipal();
        return tokenService.gerarToken(client);
    }

    @PostMapping("/uploadfile")
    public ExcelFile uploadFile(@RequestParam("file")MultipartFile file) throws Exception{
     return excelFileService.uploadFile(file);
    }

    @GetMapping("/getloggeduser")
    public Client getLoggedUser(){
        Client user = this.clientService.getLoggedUser();
        return user;
    }
    @GetMapping("/testevalor")
    public  List<Product> ddad() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            List<OrderEntity> orderEntityList = client.getOrderEntityList();
            List<Product> productList = orderEntityList.get(0).getProductList();
            return productList;
        }
        return null;
    }




}
