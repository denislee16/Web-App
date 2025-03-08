package NIRS.controller;

import NIRS.dto.ClientsDTO;
import NIRS.entity.Clients;
import NIRS.service.ClientsService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageController {

    @Autowired
    private ClientsService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/adminPage";
    }

    @GetMapping
    public String index(){
        return "user/mainPage"; // имя файла без расширения
    }

    @GetMapping("/login")
    public String loginForm(){
        return "user/login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("client", new ClientsDTO());
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("client") ClientsDTO clientsDto) {
        Clients client = Clients.builder()
                .name(clientsDto.getName())
                .number(clientsDto.getNumber())
                .email(clientsDto.getEmail())
                .password(clientsDto.getPassword())
                .role("USER") // По умолчанию даём роль "USER"
                .build();
        clientService.create(client, passwordEncoder);
        return "redirect:user/login"; // Перенаправляем на страницу входа после регистрации
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Clients client = clientService.readOne(email);
        model.addAttribute("client", client);
        return "user/profile";
    }
}
