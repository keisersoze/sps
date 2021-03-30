package com.unive.spsproject.service;

import com.unive.spsproject.model.Query1ResultDto;
import com.unive.spsproject.model.Query2ResultDto;
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

    @Transactional(readOnly = true)
    public List<Query2ResultDto> query2(LocalDate lowerDate, LocalDate upperDate, Double minDelay) {
        List<Object[]> rows = entityManager.createNativeQuery(
                "SELECT f.id, f.fl_date, f.origin_city_name, f.dest_city_name\n" +
                        "FROM flights f\n" +
                        "WHERE f.arr_delay > :minDelay AND f.fl_date between :lowerDate and :upperDate")
                .setParameter("minDelay", minDelay)
                .setParameter("lowerDate", Date.valueOf(lowerDate))
                .setParameter("upperDate", Date.valueOf(upperDate))
                .getResultList();

        List<Query2ResultDto> query2ResultDtoList = new ArrayList<>();
        for (Object[] row: rows) {
            long id = Long.valueOf((Integer) row[0]);
            LocalDate flDate = ((Date) row[1]).toLocalDate();
            String originCityName = (String) row[2];
            String destCityName = (String) row[3];
            Query2ResultDto query2ResultDto = new Query2ResultDto(id, flDate, originCityName, destCityName);
            query2ResultDtoList.add(query2ResultDto);
        }

        return query2ResultDtoList;
    }

}
