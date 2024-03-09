package com.tp.exam.repository;


import com.tp.exam.model.CommandeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommandeRepo extends JpaRepository<CommandeModel, Integer> {


    List<CommandeModel> findByUser_Email(String email);
}
