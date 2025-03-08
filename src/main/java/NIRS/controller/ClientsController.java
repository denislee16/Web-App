package NIRS.controller;

import NIRS.dto.ClientsDTO;
import NIRS.entity.CatalogOfService;
import NIRS.entity.Clients;
import NIRS.service.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class ClientsController {

    @Autowired
    private ClientsService clientsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile/clients")
    public ResponseEntity<Clients> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = authentication.getName();
        Clients client = clientsService.readOne(email);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/admin/clients")
    @ResponseBody
    public ResponseEntity<String> createClient(@RequestBody Clients newClient) {
        newClient.setRole("USER");
        clientsService.create(newClient, passwordEncoder);
        return new ResponseEntity<>("Клиент успешно создан", HttpStatus.OK);
    }

    @PutMapping("/admin/clients/{id}")
    @ResponseBody
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody Clients updatedClient){
        Clients existingClient = clientsService.readById(id);
        if (existingClient == null) {
            return new ResponseEntity<>("Клиент не найден", HttpStatus.NOT_FOUND);
        }
        existingClient.setName(updatedClient.getName());
        existingClient.setNumber(updatedClient.getNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPassword(updatedClient.getPassword());
        existingClient.setRole(updatedClient.getRole());
        clientsService.update(existingClient);
        return new ResponseEntity<>("Клиент успешно обновлен", HttpStatus.OK);
    }

    @GetMapping("/admin/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientsService.readAll());
        return "/admin/clients";
    }

    @GetMapping("/admin/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientsService.delete(id);
        return "redirect:/admin/clients";
    }

}
