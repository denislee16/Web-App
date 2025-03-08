package NIRS.controller;

import NIRS.entity.Suppliers;
import NIRS.entity.TransporationCompanies;
import NIRS.service.TransporationCompaniesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class TransporationCompaniesController {
    @Autowired
    private TransporationCompaniesService transporationCompaniesService;

    @PostMapping("/admin/transporation_companies")
    @ResponseBody
    public ResponseEntity<String> createTransporationCompany(@RequestBody TransporationCompanies transporationCompanies) {
        transporationCompaniesService.create(transporationCompanies);
        return new ResponseEntity<>("Компания успешно создана", HttpStatus.OK);
    }

    @PutMapping("/admin/transporation_companies/{id}")
    @ResponseBody
    public ResponseEntity<String> updateTransporationCompany(@PathVariable Long id, @RequestBody TransporationCompanies updatecCompany){
        TransporationCompanies existingCompany = transporationCompaniesService.readById(id);
        if (existingCompany == null) {
            return new ResponseEntity<>("Компания не найдена", HttpStatus.NOT_FOUND);
        }
        existingCompany.setName(updatecCompany.getName());
        existingCompany.setNumber(updatecCompany.getNumber());
        existingCompany.setAddress(updatecCompany.getAddress());

        transporationCompaniesService.update(existingCompany);
        return new ResponseEntity<>("Компания успешно обновлена", HttpStatus.OK);
    }

    @GetMapping("/admin/transporation_companies")
    public String listTransporationCompanies(Model model) {
        model.addAttribute("transporationCompanies", transporationCompaniesService.readAll());
        return "admin/transporationCompanies";
    }

    @GetMapping("/admin/transporation_companies/delete/{id}")
    public String deleteTransporationCompany(@PathVariable Long id) {
        transporationCompaniesService.delete(id);
        return "redirect:/admin/transporation_companies";
    }
}
