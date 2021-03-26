package com.unive.spsproject.service;

import com.unive.spsproject.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class FlightService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(Flight flight) {
        entityManager.persist(flight);
    }

    @Transactional
    public void insertWithQuery(Flight flight) {
        entityManager.createNativeQuery("INSERT INTO flight (id,name) VALUES (?,?)")
                .setParameter(1, flight.getId() )
                .setParameter(2, flight.getName())
                .executeUpdate();
    }

}
