package com.tp.exam.repository;

import com.tp.exam.model.ParfumModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParfumRepo extends JpaRepository<ParfumModel, Integer> {
}
