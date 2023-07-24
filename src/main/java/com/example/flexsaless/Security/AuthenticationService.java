package com.example.flexsaless.Security;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("Email not found"));


        return new Client(client.getId(),client.getClientName(), client.getEmail(), client.getPassword());
    }
}
