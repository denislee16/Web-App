package NIRS.controller;

import NIRS.entity.*;
import NIRS.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping("/purchase_histories")
@AllArgsConstructor
public class PurchaseHistoriesController {

    @Autowired
    private PurchaseHistoriesService purchaseHistoriesService;
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private WorkersService workersService;
    @Autowired
    private CarsService carsService;
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/admin/purchase_histories")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody PurchaseHistories purchaseHistories) {
//        if (purchaseHistories.getClients() != null && purchaseHistories.getClients().getClient_id() != null)
//        {
//            Clients clients = clientsService.readById(purchaseHistories.getClients().getClient_id());
//            purchaseHistories.setClients(clients);
//        } else {
//            purchaseHistories.setClients(null);
//        }
//        if (purchaseHistories.getClients() != null && purchaseHistories.getClients().getClient_id() != null)
//        {
//            Clients clients = clientsService.readById(purchaseHistories.getClients().getClient_id());
//            purchaseHistories.setClients(clients);
//        } else {
//            purchaseHistories.setClients(null);
//        }
//        if (purchaseHistories.getClients() != null && purchaseHistories.getClients().getClient_id() != null)
//        {
//            Clients clients = clientsService.readById(purchaseHistories.getClients().getClient_id());
//            purchaseHistories.setClients(clients);
//        } else {
//            purchaseHistories.setClients(null);
//        }
//        if (purchaseHistories.getClients() != null && purchaseHistories.getClients().getClient_id() != null)
//        {
//            Clients clients = clientsService.readById(purchaseHistories.getClients().getClient_id());
//            purchaseHistories.setClients(clients);
//        } else {
//            purchaseHistories.setClients(null);
//        }
        purchaseHistoriesService.create(purchaseHistories);
        return new ResponseEntity<>("Покупка успешно создана", HttpStatus.OK);
    }

    @PutMapping("/admin/purchase_histories/{id}")
    @ResponseBody
    public ResponseEntity<String> updatePurchase(@PathVariable Long id, @RequestBody PurchaseHistories updatedPurchase) {
        PurchaseHistories existingPurchase = purchaseHistoriesService.readById(id);
        if (existingPurchase == null) {
            return new ResponseEntity<>("Покупка не найдена", HttpStatus.NOT_FOUND);
        }
        existingPurchase.setDate(updatedPurchase.getDate());
        existingPurchase.setTotalCost(updatedPurchase.getTotalCost());

        Clients clients = clientsService.readById(updatedPurchase.getClients().getClient_id());
        Workers workers = workersService.readById(updatedPurchase.getWorkers().getWorker_id());
       // Cars cars = carsService.readById(updatedPurchase.getCars().getCar_id());
        //Service service = serviceService.readById(updatedPurchase.getService().getService_id());
        existingPurchase.setClients(clients);
        existingPurchase.setWorkers(workers);
       // existingPurchase.setCars(cars);
        //existingPurchase.setService(service);

        purchaseHistoriesService.update(existingPurchase);
        return new ResponseEntity<>("Покупка успешно обновлена", HttpStatus.OK);
    }

    @GetMapping("/admin/purchase_histories")
    public String listPurchaseHistories(Model model) {
        model.addAttribute("purchaseHistories", purchaseHistoriesService.readAll());
        return "admin/purchaseHistories";
    }

    @GetMapping("/admin/purchase_histories/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        purchaseHistoriesService.delete(id);
        return "redirect:/admin/purchase_histories";
    }

}
