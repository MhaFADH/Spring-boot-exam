package com.tp.exam.controller;

import com.tp.exam.service.ParfumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ParfumService parfumService;
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("parfums", parfumService.getAllParfums());

        return "index";
    }

    @GetMapping("/parfum")
    public String detail(Model model,@RequestParam(value = "id",required = true) Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName().equals("anonymousUser"));
        model.addAttribute("parfum", parfumService.getParfumById(id));
        return "detail";
    }
}
