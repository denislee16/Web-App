package NIRS.service;

import NIRS.entity.Clients;
import NIRS.entity.Suppliers;
import NIRS.entity.TransporationCompanies;
import NIRS.entity.Workers;
import NIRS.repository.SuppliersRepository;
import NIRS.repository.TransporationCompaniesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SuppliersService {
    private SuppliersRepository suppliersRepository;

    public List<Suppliers> readAll() {
        return suppliersRepository.findAll();
    }

    public Suppliers create(Suppliers suppliers) {
        return suppliersRepository.save(suppliers);
    }

    public Suppliers update(Suppliers suppliers) {
        return suppliersRepository.save(suppliers);
    }

    public void delete(Long id) {
        suppliersRepository.deleteById(id);
    }

    public Suppliers readById(Long id) {
        return suppliersRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier " + id + " not found"));
    }
}
