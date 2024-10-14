package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.persistence.entity.Client;


public interface ClientService {

    Optional<Client> getClientById(String id);

    List<Client> getAllCLients();

    Client createClient(Client client);

    Client updateClient(String id, Client updatedClient);

    void deleteClient(String id);

}
