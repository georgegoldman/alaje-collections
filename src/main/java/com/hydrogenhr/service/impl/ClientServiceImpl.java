package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.Client;
import com.hydrogenhr.persistence.repository.ClientRepository;
import com.hydrogenhr.service.ClientService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public Optional<Client> getClientById(String id) {
     return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllCLients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);   
    }

    @Override
    public Client updateClient(String id, Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setClientId(updatedClient.getClientId());
            client.setClientIdIssuedAt(updatedClient.getClientIdIssuedAt());
            client.setClientSecret(updatedClient.getClientSecret());
            client.setClientSecretExpiresAt(updatedClient.getClientSecretExpiresAt());
            client.setClientName(updatedClient.getClientName());
            client.setClientAuthenticationMethods(updatedClient.getClientAuthenticationMethods());
            client.setAuthorizationGrantTypes(updatedClient.getAuthorizationGrantTypes());
            client.setRedirectUris(updatedClient.getRedirectUris());
            client.setPostLogoutRedirectUris(updatedClient.getPostLogoutRedirectUris());
            client.setScopes(updatedClient.getScopes());
            client.setClientSettings(updatedClient.getClientSettings());
            client.setTokenSettings(updatedClient.getTokenSettings());
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

}
