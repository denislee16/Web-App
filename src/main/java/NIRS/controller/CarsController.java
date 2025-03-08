package NIRS.controller;

import NIRS.dto.CarsDTO;
import NIRS.entity.Cars;
import NIRS.entity.CatalogOfService;
import NIRS.entity.Suppliers;
import NIRS.entity.TransporationCompanies;
import NIRS.service.CarsService;
import NIRS.service.CatalogOfServiceService;
import NIRS.service.SuppliersService;
import NIRS.service.TransporationCompaniesService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CarsController {

    @Autowired
    private CarsService carsService;
    @Autowired
    private SuppliersService suppliersService;
    @Autowired
    private TransporationCompaniesService transporationCompaniesService;
    @Autowired
    private CatalogOfServiceService catalogOfServiceService;

    @PostMapping("/admin/cars")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody Cars cars) {
        if (cars.getSuppliers() != null && cars.getSuppliers().getSupplier_id() != null)
        {
            Suppliers supplier = suppliersService.readById(cars.getSuppliers().getSupplier_id());
            cars.setSuppliers(supplier);
        } else {
            cars.setSuppliers(null);
        }
        carsService.create(cars);
        return new ResponseEntity<>("Автомобиль успешно создан", HttpStatus.OK);
    }

    @PutMapping("/admin/cars/{id}")
    @ResponseBody
    public ResponseEntity<String> updateCar(@PathVariable Long id, @RequestBody Cars updatedCar){
        Cars existingCar = carsService.readById(id);
        if (existingCar == null) {
            return new ResponseEntity<>("Автомобиль не найден", HttpStatus.NOT_FOUND);
        }
        existingCar.setModel(updatedCar.getModel());
        existingCar.setCost(updatedCar.getCost());
        existingCar.setMakeYear(updatedCar.getMakeYear());
        existingCar.setMileage(updatedCar.getMileage());
        existingCar.setColor(updatedCar.getColor());
        if (updatedCar.getSuppliers() != null && updatedCar.getSuppliers().getSupplier_id() != null && updatedCar.getTransporationCompanies() != null && updatedCar.getTransporationCompanies().getTransporation_id() != null) {
            Suppliers suppliers = suppliersService.readById(updatedCar.getSuppliers().getSupplier_id());
            TransporationCompanies transporationCompanies = transporationCompaniesService.readById(updatedCar.getTransporationCompanies().getTransporation_id());
            existingCar.setSuppliers(suppliers);
            existingCar.setTransporationCompanies(transporationCompanies);
        }
        carsService.update(existingCar);
        return new ResponseEntity<>("Автомобиль успешно обновлен", HttpStatus.OK);
    }

    @GetMapping("/admin/cars")
    public String сars(Model model) {
        model.addAttribute("cars", carsService.readAll());
        return "admin/cars";
    }

    @GetMapping("/cars-user")
    public String cars() {
        return "user/cars-user";
    }

    @GetMapping("/cars-user/list")
    public @ResponseBody List<Cars> getCars() {
        return carsService.readAll();
    }

    @GetMapping("/admin/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carsService.delete(id);
        return "redirect:/admin/cars";
    }
}
