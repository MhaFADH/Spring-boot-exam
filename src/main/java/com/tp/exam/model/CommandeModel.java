package com.tp.exam.model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity(name = "commandes")
@Data
public class CommandeModel {

    public CommandeModel() {
        this.date =  new Date();
        this.etat = "En attente";
    }

    @Id
    @GeneratedValue( strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private Double montant;

    private String etat;

    @ManyToOne
    @JoinColumn(name = "parfum_id")
    private ParfumModel parfum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
