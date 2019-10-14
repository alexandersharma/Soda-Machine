package com.alex.interview.sodamachine.dao;

import com.alex.interview.sodamachine.entity.Soda;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class InventoryDaoImpl implements InventoryDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Soda> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Soda> query = currentSession.createQuery("from Soda", Soda.class);
        List<Soda> sodas = query.getResultList();

        return sodas;
    }

    @Override
    public Soda findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Soda soda = currentSession.get(Soda.class,id);

        return soda;
    }

    @Override
    public void save(Soda soda) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(soda);
    }
}
