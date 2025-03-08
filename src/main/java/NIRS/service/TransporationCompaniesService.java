package NIRS.service;

import NIRS.entity.Clients;
import NIRS.entity.TransporationCompanies;
import NIRS.entity.Workers;
import NIRS.repository.TransporationCompaniesRepository;
import NIRS.repository.WorkersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransporationCompaniesService {
    private TransporationCompaniesRepository transporationCompaniesRepository;

    public List<TransporationCompanies> readAll() {
        return transporationCompaniesRepository.findAll();
    }

    public TransporationCompanies create(TransporationCompanies transporationCompanies) {
        return transporationCompaniesRepository.save(transporationCompanies);
    }

    public TransporationCompanies update(TransporationCompanies transporationCompanies) {
        return transporationCompaniesRepository.save(transporationCompanies);
    }

    public void delete(Long id) {
        transporationCompaniesRepository.deleteById(id);
    }

    public TransporationCompanies readById(Long id) {
        return transporationCompaniesRepository.findById(id).orElseThrow(() -> new RuntimeException("players " + id + " not found"));
    }
}
