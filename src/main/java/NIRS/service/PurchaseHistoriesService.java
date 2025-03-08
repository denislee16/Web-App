package NIRS.service;

import NIRS.controller.CartController;
import NIRS.dto.PurchaseHistoriesDTO;
import NIRS.entity.*;
import NIRS.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseHistoriesService {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private WorkersRepository workersRepository;
    @Autowired
    private CatalogOfServiceRepository catalogOfServiceRepository;
    @Autowired
    private PurchaseHistoriesRepository purchaseHistoriesRepository;
    @Autowired
    private ServiceService serviceService;

    public List<PurchaseHistories> readAll() {
        return purchaseHistoriesRepository.findAll();
    }

//    public PurchaseHistories create(PurchaseHistoriesDTO purchaseHistoriesDTO) {
//        return purchaseHistoriesRepository.save(PurchaseHistories.builder()
//                .date(purchaseHistoriesDTO.getDate())
//                .totalCost(purchaseHistoriesDTO.getTotalCost())
//                .clients(clientsService.readById(purchaseHistoriesDTO.getClient_id()))
//                .workers(workersService.readById(purchaseHistoriesDTO.getWorker_id()))
//                .cars(carsService.readById(purchaseHistoriesDTO.getCar_id()))
//                .service(serviceService.readById(purchaseHistoriesDTO.getService_id()))
//                .build());
//    }

    public void create(PurchaseHistories purchaseHistories) {
        purchaseHistoriesRepository.save(purchaseHistories);
    }

//    public List<PurchaseHistories> findByClientId(Long clientId){
//        List<Cars> cars =  carsRepository.findByClient_ClientId(clientId);
//        List<PurchaseHistories> orders = new ArrayList<>();
//        for (Cars car : cars) {
//            List<PurchaseHistories> ordersByCarId = purchaseHistoriesRepository.findByCar_CarId(car.getCar_id());
//            orders.addAll(ordersByCarId);
//        }
//        return orders;
//    }

    public PurchaseHistories createPurchaseFromCart(List<CartController.CartItem> cart, Long client_id, Long worker_id) {
        PurchaseHistories purchase = new PurchaseHistories();
        Long default_id = 1L;
       // Cars car = new Cars();
        NIRS.entity.Service service = new NIRS.entity.Service();
        Cars car = carsRepository.findById(default_id).orElse(new Cars());
        CatalogOfService catalogOfService = catalogOfServiceRepository.findById(default_id).orElse(new CatalogOfService());
        int totalCost = 0;

        for (CartController.CartItem cartItem : cart) {
            if (cartItem.isService()) {
                Optional<CatalogOfService> serviceOptional = catalogOfServiceRepository.findById(cartItem.getId());
                if (serviceOptional.isPresent()) {
                    catalogOfService = serviceOptional.get();
                }
//                else{
//                    Long default_id = 1L;
//                    service = serviceRepository.findById(default_id).orElse(new NIRS.entity.Service());
//                }
            } else {
                Optional<Cars> carOptional = carsRepository.findById(cartItem.getId());
                if (carOptional.isPresent()) {
                    car = carOptional.get();
                }
//                else {
//                    Long default_id = 1L;
//                    car = carsRepository.findById(default_id).orElse(new Cars());
//                }
            }
            totalCost += cartItem.getPrice() * cartItem.getQuantity();
        }

        purchase.setWorkers(workersRepository.findById(worker_id).orElse(new Workers()));
        purchase.setCars(car);
        purchase.setDate(new Date());
        purchase.setTotalCost(totalCost);
        purchase.setClients(clientsRepository.findById(client_id).orElse(new Clients()));

        service.setCatalogOfService(catalogOfService);
        service.setDate(purchase.getDate());

        purchase.setService(service);
        serviceService.create(service);

        return purchaseHistoriesRepository.save(purchase);
    }

    public PurchaseHistories update(PurchaseHistories purchaseHistories) {
        return purchaseHistoriesRepository.save(purchaseHistories);
    }

    public void delete(Long id) {
        purchaseHistoriesRepository.deleteById(id);
    }

    public PurchaseHistories readById(Long id) {
        return purchaseHistoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("purchase " + id + " not found"));
    }
}
