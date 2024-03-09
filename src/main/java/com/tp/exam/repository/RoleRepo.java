package com.tp.exam.repository;

import com.tp.exam.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleModel, Integer> {
    RoleModel findByNom(String admin);
}
