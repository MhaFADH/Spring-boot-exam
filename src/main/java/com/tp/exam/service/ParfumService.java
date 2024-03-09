package com.tp.exam.service;

import com.tp.exam.model.ParfumModel;
import com.tp.exam.repository.ParfumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParfumService {

    @Autowired
    private ParfumRepo parfumRepo;

    public List<ParfumModel> getAllParfums() {
        return parfumRepo.findAll();
    }

    public ParfumModel getParfumById(int id) {
        return parfumRepo.findById(id).get();
    }

    public ParfumModel save(ParfumModel parfum) {
        return parfumRepo.save(parfum);
    }

    public void delete(ParfumModel parfum) {
        parfumRepo.delete(parfum);
    }
}
