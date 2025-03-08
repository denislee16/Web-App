package NIRS.controller;

import NIRS.dto.ServiceDTO;
import NIRS.entity.*;
import NIRS.service.CatalogOfServiceService;
import NIRS.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CatalogOfServiceService catalogOfServiceService;

    @PostMapping("/admin/service")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody Service service) {
        if (service.getCatalogOfService() != null && service.getCatalogOfService().getCatalog_id() != null)
        {
            CatalogOfService catalogOfService = catalogOfServiceService.readById(service.getCatalogOfService().getCatalog_id());
            service.setCatalogOfService(catalogOfService);
        } else {
            service.setCatalogOfService(null);
        }
        serviceService.create(service);
        return new ResponseEntity<>("Операция успешно выполнена", HttpStatus.OK);
    }

    @PutMapping("/admin/service/{id}")
    @ResponseBody
    public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody Service updatedService){
        Service existingService = serviceService.readById(id);
        if (existingService == null) {
            return new ResponseEntity<>("Операция не найдена", HttpStatus.NOT_FOUND);
        }
        existingService.setDate(updatedService.getDate());
        if (updatedService.getCatalogOfService() != null && updatedService.getCatalogOfService().getCatalog_id() != null) {
            CatalogOfService catalogOfService = catalogOfServiceService.readById(updatedService.getCatalogOfService().getCatalog_id());
            existingService.setCatalogOfService(catalogOfService);
        }
        serviceService.update(existingService);
        return new ResponseEntity<>("Операция успешно обновлена", HttpStatus.OK);
    }

    @GetMapping("/admin/service")
    public String listService(Model model) {
        model.addAttribute("services", serviceService.readAll());
        return "admin/service";
    }

    @GetMapping("/admin/service/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.delete(id);
        return "redirect:/admin/service";
    }
}
