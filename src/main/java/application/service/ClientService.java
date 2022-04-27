package application.service;

import application.dao.impl.ClientRepository;
import application.model.Client;

import java.util.Collection;

public class ClientService implements Service<Client> {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Collection<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client add(Client entity) {
        return clientRepository.create(entity);
    }

    @Override
    public Client update(Client entity) {
        return clientRepository.update(entity);
    }

    @Override
    public Client delete(Integer id) {
        return clientRepository.deleteById(id);
    }

    public Client findClientByUsername(String username){
        var client = clientRepository.findByUsername(username);
        if (client != null) {
            return client;
        }
        return null;
    }
}
