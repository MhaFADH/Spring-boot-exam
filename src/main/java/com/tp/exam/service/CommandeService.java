package com.tp.exam.service;

import com.tp.exam.model.CommandeModel;
import com.tp.exam.repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepo commandeRepo;

    public List<CommandeModel> getAllCommandes() {
        return this.commandeRepo.findAll();
    }

    public List<CommandeModel> getAllCommandesByUser(String email) {
        return commandeRepo.findByUser_Email(email);
    }
    public void save(CommandeModel commande) {
        this.commandeRepo.save(commande);
    }
}
