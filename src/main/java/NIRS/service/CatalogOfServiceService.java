package NIRS.service;

import NIRS.entity.Cars;
import NIRS.entity.CatalogOfService;
import NIRS.entity.Clients;
import NIRS.entity.Workers;
import NIRS.repository.CatalogOfServiceRepository;
import NIRS.repository.ClientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogOfServiceService {
    private CatalogOfServiceRepository catalogOfServiceRepository;

    public List<CatalogOfService> readAll() {
        return catalogOfServiceRepository.findAll();
    }

    public CatalogOfService create(CatalogOfService catalogOfService) {
        return catalogOfServiceRepository.save(catalogOfService);
    }

    public CatalogOfService update(CatalogOfService catalogOfService) {
        return catalogOfServiceRepository.save(catalogOfService);
    }

    public void delete(Long id) {
        catalogOfServiceRepository.deleteById(id);
    }

    public CatalogOfService readById(Long id) {
        return catalogOfServiceRepository.findById(id).orElseThrow(() -> new RuntimeException("players " + id + " not found"));
    }
}
