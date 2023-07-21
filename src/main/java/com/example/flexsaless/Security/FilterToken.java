package com.example.flexsaless.Security;

import com.example.flexsaless.Repository.ClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ClientRepository clientRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorizarHeader = request.getHeader("Authorization");
        if(authorizarHeader != null){
            token = authorizarHeader.replace("Bearer ", "");
            var subject = this.tokenService.getSubject(token);

            var usuario = this.clientRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }

    private void liberacaoCors(HttpServletResponse response) {
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if(response.getHeader("Access-Control-Allow-Headers") == null){
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if(response.getHeader("Access-Control-Request-Headers") == null){
            response.addHeader("Access-Control-Request-Headers", "*");
        }
        if(response.getHeader("Access-Control-Allow-Methods") == null){
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }
}
