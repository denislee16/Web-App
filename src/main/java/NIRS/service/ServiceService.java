package NIRS.service;

import NIRS.dto.ServiceDTO;
import NIRS.entity.Cars;
import NIRS.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {
    private ServiceRepository serviceRepository;
    private CatalogOfServiceService catalogOfServiceService;

    public List<NIRS.entity.Service> readAll() {
        return serviceRepository.findAll();
    }

//    public NIRS.entity.Service create(ServiceDTO serviceDTO) {
//        return serviceRepository.save(NIRS.entity.Service.builder()
//                .date(serviceDTO.getDate())
//                .catalogOfService(catalogOfServiceService.readById(serviceDTO.getCatalog_id()))
//                .build());
//    }

    public void create(NIRS.entity.Service service){
        serviceRepository.save(service);
    }

    public NIRS.entity.Service update(NIRS.entity.Service service) {
        return serviceRepository.save(service);
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    public NIRS.entity.Service readById(Long id) {
        return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("service " + id + " not found"));
    }
}
