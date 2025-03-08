package NIRS.controller;


import NIRS.entity.Cars;
import NIRS.entity.CatalogOfService;
import NIRS.entity.Suppliers;
import NIRS.entity.TransporationCompanies;
import NIRS.service.CatalogOfServiceService;
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
public class CatalogOfServiceController {
    @Autowired
    private CatalogOfServiceService catalogOfServiceService;

    @GetMapping("/catalogOfService-user")
    public String catalogOfService() {
        return "user/catalogOfService-user";
    }

    @GetMapping("/catalogOfService-user/list")
    public @ResponseBody List<CatalogOfService> getCatalog() {
        return catalogOfServiceService.readAll();
    }

    @PostMapping("/admin/catalog")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody CatalogOfService catalogOfService) {
      catalogOfServiceService.create(catalogOfService);
        return new ResponseEntity<>("Услуга успешно создана", HttpStatus.OK);
    }

    @PutMapping("/admin/catalog/{id}")
    @ResponseBody
    public ResponseEntity<String> updateCatalog(@PathVariable Long id, @RequestBody CatalogOfService updatedCatalog){
        CatalogOfService existingCatalog = catalogOfServiceService.readById(id);
        if (existingCatalog == null) {
            return new ResponseEntity<>("Услуга не найдена", HttpStatus.NOT_FOUND);
        }
        existingCatalog.setName(updatedCatalog.getName());
        existingCatalog.setCost(updatedCatalog.getCost());
        catalogOfServiceService.update(existingCatalog);
        return new ResponseEntity<>("Услуга успешно обновлена", HttpStatus.OK);
    }

    @GetMapping("/admin/catalog")
    public String listCatalogOfService(Model model) {
        model.addAttribute("catalogOfServices", catalogOfServiceService.readAll());
        return "admin/catalogOfService";
    }

    @GetMapping("/admin/catalog/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        catalogOfServiceService.delete(id);
        return "redirect:/admin/catalog";
    }
}
