package com.tp.exam.repository;

import com.tp.exam.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(@NonNull String email);

    boolean existsByEmail(String email);
}
