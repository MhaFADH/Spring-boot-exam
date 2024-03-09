package com.tp.exam.controller;

import com.tp.exam.model.ParfumModel;
import com.tp.exam.service.CommandeService;
import com.tp.exam.service.ParfumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {
    @Autowired
    ParfumService parfumService;

    @Autowired
    CommandeService commandeService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(required = false) Integer id) {
        ParfumModel parfum = new ParfumModel();
        if(id != null) {
            parfum = parfumService.getParfumById(id);
        }

        model.addAttribute("parfums", parfumService.getAllParfums());
        model.addAttribute("parfum", parfum);
        model.addAttribute("commandes", commandeService.getAllCommandes());

        return "dashboard";
    }

    @PostMapping("/dashboard/recupForm")
    public String recupForm(@ModelAttribute ParfumModel parfum) {

        if(parfum.getId() != null) {
            parfum.setId(parfum.getId());
        }

        parfumService.save(parfum);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/delete")
    public String deleteOrActivateBien(@RequestParam(required = false) Integer id) {
        ParfumModel parfum =parfumService.getParfumById(id);
        parfumService.delete(parfum);
        return "redirect:/dashboard";
    }
}
