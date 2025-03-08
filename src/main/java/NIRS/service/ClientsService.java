package NIRS.service;

import NIRS.entity.Clients;
import NIRS.entity.Suppliers;
import NIRS.entity.Workers;
import NIRS.repository.ClientsRepository;
import NIRS.repository.SuppliersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientsService {
    private ClientsRepository clientsRepository;

    public List<Clients> readAll() {
        return clientsRepository.findAll();
    }

    public Clients readOne(String email) {
        return clientsRepository.findByEmail(email);
    }

    public Clients create(Clients clients, PasswordEncoder passwordEncoder) {
        clients.setPassword(passwordEncoder.encode(clients.getPassword()));
        return clientsRepository.save(clients);
    }

    public Clients update(Clients clients) {
        return clientsRepository.save(clients);
    }

    public void delete(Long id) {
        clientsRepository.deleteById(id);
    }

    public Clients readById(Long id) {
        return clientsRepository.findById(id).orElseThrow(() -> new RuntimeException("clients " + id + " not found"));
    }
}
