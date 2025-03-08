package NIRS.controller;

import NIRS.entity.Clients;
import NIRS.entity.Suppliers;
import NIRS.service.SuppliersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;

    @PostMapping("/admin/suppliers")
    @ResponseBody
    public ResponseEntity<String> createSupplier(@RequestBody Suppliers newSupplier) {
        suppliersService.create(newSupplier);
        return new ResponseEntity<>("Поставщик успешно создан", HttpStatus.OK);
    }

    @PutMapping("/admin/suppliers/{id}")
    @ResponseBody
    public ResponseEntity<String> updateSupplier(@PathVariable Long id, @RequestBody Suppliers updateSupplier){
        Suppliers existingSupplier = suppliersService.readById(id);
        if (existingSupplier == null) {
            return new ResponseEntity<>("Поставщик не найден", HttpStatus.NOT_FOUND);
        }
        existingSupplier.setName(updateSupplier.getName());
        existingSupplier.setNumber(updateSupplier.getNumber());
        existingSupplier.setAddress(updateSupplier.getAddress());

        suppliersService.update(existingSupplier);
        return new ResponseEntity<>("Поставщик успешно обновлен", HttpStatus.OK);
    }

    @GetMapping("/admin/suppliers")
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", suppliersService.readAll());
        return "admin/suppliers";
    }

    @GetMapping("/admin/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        suppliersService.delete(id);
        return "redirect:/admin/suppliers";
    }
}
