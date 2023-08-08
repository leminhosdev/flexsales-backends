package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.OrderEntity;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void save(OrderEntity orderEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            orderEntity.setClientOrderOwner(client);
            List<OrderEntity> orderEntityList = new ArrayList<>();
            orderEntityList.add(orderEntity);
            client.setOrderEntityList(orderEntityList);
            this.orderRepository.save(orderEntity);
            this.clientRepository.save(client);
        }

    }
}
