package com.tp.exam.controller;

import com.tp.exam.model.CommandeModel;
import com.tp.exam.model.ParfumModel;
import com.tp.exam.model.UserModel;
import com.tp.exam.service.CommandeService;
import com.tp.exam.service.ParfumService;
import com.tp.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    ParfumService parfumService;
    @Autowired
    UserService userService;
    @Autowired
    private CommandeService commandeService;


    @GetMapping("/client")
    public String client(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByEmail(auth.getName());
        model.addAttribute("userInfos", user);
        model.addAttribute("commandes", commandeService.getAllCommandesByUser(auth.getName()));

        return "client";
    }

    @GetMapping("/parfum/commande")
    public String commander(@RequestParam(value = "id",required = true) Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ParfumModel parfum = parfumService.getParfumById(id);
        UserModel user = userService.getUserByEmail(auth.getName());
        CommandeModel newCommande = new CommandeModel();
        newCommande.setParfum(parfum);
        newCommande.setUser(user);
        newCommande.setMontant(parfum.getPrix());
        commandeService.save(newCommande);

        return "redirect:/";
    }
}
