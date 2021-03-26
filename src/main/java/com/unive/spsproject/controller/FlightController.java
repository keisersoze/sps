package com.unive.spsproject.controller;

import com.unive.spsproject.model.Flight;
import com.unive.spsproject.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Create flight")
    @GetMapping(value = "/hello")
    public String createFlight() {
        return "hello";
    }

    @Operation(summary = "Create flight")
    @PostMapping(value = "/flights",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void createFlight(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "A JSON representation of the flight")
                                 @RequestBody
                                 @NotNull Flight flight) {
        flightService.insertWithQuery(flight);
    }

}
