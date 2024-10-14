package com.hydrogenhr.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.Client;
import com.hydrogenhr.service.ClientService;

import java.util.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllCLients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getCLientById(@PathVariable String id){
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    // Create a new client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client updatedClient) {
        try {
            return ResponseEntity.ok(clientService.updateClient(id, updatedClient));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
