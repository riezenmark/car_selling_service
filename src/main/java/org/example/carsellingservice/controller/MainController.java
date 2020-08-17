package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.CarMakerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final CarMakerService makerService;

    @GetMapping
    public String initClientData(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> clientData = new HashMap<>();
        if (user != null) {
            clientData.put("profile", user);
        }
        clientData.put("makers", makerService.getMakers(null));
        model.addAttribute("clientData", clientData);
        return "index";
    }
}
