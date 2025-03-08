package NIRS.service;

import NIRS.dto.CarsDTO;
import NIRS.dto.PurchaseHistoriesDTO;
import NIRS.entity.Cars;
import NIRS.entity.CatalogOfService;
import NIRS.entity.Clients;
import NIRS.entity.PurchaseHistories;
import NIRS.repository.CarsRepository;
import NIRS.repository.CatalogOfServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarsService {
    private CarsRepository carsRepository;
    private SuppliersService suppliersService;
    private TransporationCompaniesService transporationCompaniesService;

    public List<Cars> readAll() {
        return carsRepository.findAll();
    }

    public Cars create(CarsDTO carsDTO) {
        return carsRepository.save(Cars.builder()
                .model(carsDTO.getModel())
                .cost(carsDTO.getCost())
                .makeYear(carsDTO.getMakeYear())
                .mileage(carsDTO.getMileage())
                .color(carsDTO.getColor())
                .suppliers(suppliersService.readById(carsDTO.getSupplier_id()))
                .transporationCompanies(transporationCompaniesService.readById(carsDTO.getTransporation_id()))
                .build());
    }

//    public List<Cars> findByClientId(Long clientId) {
//        return carsRepository.findByClient_ClientId(clientId);
//    }

    public void create(Cars cars){
        carsRepository.save(cars);
    }

    public Cars update(Cars cars) {
        return carsRepository.save(cars);
    }

    public void delete(Long id) {
        carsRepository.deleteById(id);
    }

    public Cars readById(Long id) {
        return carsRepository.findById(id).orElseThrow(() -> new RuntimeException("cars " + id + " not found"));
    }
}
