package com.alex.interview.sodamachine.service;

import com.alex.interview.sodamachine.dao.InventoryDao;
import com.alex.interview.sodamachine.entity.Soda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao dao;

    @Override
    @Transactional
    public List<Soda> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public Soda findById(int id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public void save(Soda soda) {
        dao.save(soda);
    }

    @Override
    @Transactional
    public int totalCount() {
        List<Soda> sodas = dao.findAll();
        int totalCount = 0;
        for (Soda item : sodas) {
            totalCount += item.getCount();
        }
        return totalCount;
    }
}
