package com.example.flexsaless.Util;

import com.example.flexsaless.Entitiy.Client;

public class ClientCreator {

    public static Client createClientToBeSaved(){
        return Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("topasdm3das").build();
    }
    public static Client createValidClient(){
        return Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("topasdm3das").id(10L).build();
    }
    public static Client createValidUpdatedClient(){
        return Client.builder().clientName("Peopleschan").clientArea("Business").email("ada3ff@gmail.com")
                .password("topasdm3das").id(10L).build();
    }
}
