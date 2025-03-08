package NIRS.service;

import NIRS.entity.Clients;
import NIRS.entity.Workers;
import NIRS.repository.WorkersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkersService {
    private WorkersRepository workersRepository;

    public List<Workers> readAll() {
        return workersRepository.findAll();
    }

    public Workers create(Workers workers) {
        return workersRepository.save(workers);
    }

    public Workers update(Workers workers) {
        return workersRepository.save(workers);
    }

    public void delete(Long id) {
        workersRepository.deleteById(id);
    }
    public Workers readById(Long id) {
        return workersRepository.findById(id).orElseThrow(() -> new RuntimeException("players " + id + " not found"));
    }
}
