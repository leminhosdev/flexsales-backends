package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Security.TokenService;
import com.example.flexsaless.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class ClientController {
    @Autowired
    private TokenService tokenService;
    private ClientService clientService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @CrossOrigin(origins ="http://localhost:4200")
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

    @PutMapping("/uploadfile")
    public ResponseEntity<String> uploadFile(@RequestParam("excel")MultipartFile file){
      try{  this.clientService.uploadFile(file);
        return ResponseEntity.ok("file saved!");
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file.");
    }
    }

    @GetMapping("/getloggeduser")
    public Client getLoggedUser(){
        Client loggedUser = this.clientService.getLoggedUser();
        return loggedUser;
    }
}
