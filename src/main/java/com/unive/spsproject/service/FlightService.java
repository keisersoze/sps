package com.unive.spsproject.service;

import com.unive.spsproject.model.Query1ResultDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlightService {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Transactional
    public void insertWithEntityManager(Flight flight) {
        entityManager.persist(flight);
    }

    @Transactional()
    public void insertWithQuery(Flight flight) {
        entityManager.createNativeQuery("INSERT INTO flight (id,name) VALUES (?,?)")
                .setParameter(1, flight.getId() )
                .setParameter(2, flight.getName())
                .executeUpdate();
    }
    "WHERE f.fl_date == :flightDate AND f.op_carrier_fl_num == :flightNumber"

           .setParameter("flightDate", flightDate)
                .setParameter("flightNumber", flightNumber)
*/


    @Transactional(readOnly = true)
    public List<Query1ResultDto> query1(LocalDate flightDate , Integer flightNumber) {
        List<Object[]> rows = entityManager.createNativeQuery(
                "SELECT f.arr_delay, f.dep_delay\n" +
                    "FROM flights f\n" +
                    "WHERE f.op_carrier_fl_num = :flightNumber AND f.fl_date = :flightDate ")
                .setParameter("flightNumber", String.valueOf(flightNumber) )
                .setParameter("flightDate", Date.valueOf(flightDate))
                .getResultList();

        List<Query1ResultDto> query1ResultDtoList = new ArrayList<>();
        for (Object[] row: rows) {
            Double arrivalDelay = (Double) row[0];
            Double departureDelay = (Double) row[1];
            Query1ResultDto query1ResultDto = new Query1ResultDto(arrivalDelay, departureDelay);
            query1ResultDtoList.add(query1ResultDto);
        }

        return query1ResultDtoList;
    }


}
