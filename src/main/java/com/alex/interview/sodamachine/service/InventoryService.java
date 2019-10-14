package com.alex.interview.sodamachine.service;

import com.alex.interview.sodamachine.entity.Soda;

import java.util.List;

public interface InventoryService {
    List<Soda> findAll();

    Soda findById(int id);

    void save(Soda soda);

    int totalCount();
}
