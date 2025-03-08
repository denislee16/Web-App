package NIRS.controller;

import NIRS.entity.Suppliers;
import NIRS.entity.Workers;
import NIRS.service.WorkersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class WorkersController {
    private WorkersService workersService;

    @PostMapping("/admin/workers")
    @ResponseBody
    public ResponseEntity<String> createWorker(@RequestBody Workers newWorker) {
        workersService.create(newWorker);
        return new ResponseEntity<>("Работник успешно создан", HttpStatus.OK);
    }

    @PutMapping("/admin/workers/{id}")
    @ResponseBody
    public ResponseEntity<String> updateWorker(@PathVariable Long id, @RequestBody Workers updateWorker){
        Workers existingWorker = workersService.readById(id);
        if (existingWorker == null) {
            return new ResponseEntity<>("Работник не найден", HttpStatus.NOT_FOUND);
        }
        existingWorker.setName(updateWorker.getName());
        existingWorker.setPost(updateWorker.getPost());
        existingWorker.setExperience(updateWorker.getExperience());
        existingWorker.setWages(updateWorker.getWages());
        existingWorker.setNumber(updateWorker.getNumber());

        workersService.update(existingWorker);
        return new ResponseEntity<>("Работник успешно обновлен", HttpStatus.OK);
    }

    @GetMapping("/admin/workers")
    public String listWorkers(Model model) {
        model.addAttribute("workers", workersService.readAll());
        return "admin/workers";
    }

    @GetMapping("/admin/workers/delete/{id}")
    public String deleteWorker(@PathVariable Long id) {
        workersService.delete(id);
        return "redirect:/admin/workers";
    }
}
