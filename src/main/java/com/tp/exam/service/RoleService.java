package com.tp.exam.service;

import com.tp.exam.model.RoleModel;
import com.tp.exam.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public RoleModel findByNom(String admin) {
        return roleRepo.findByNom(admin);
    }

    public RoleModel save(String rle) {
        RoleModel role = new RoleModel();
        role.setNom(rle);
        return roleRepo.save(role);
    }
}
