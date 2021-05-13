package com.unive.spsproject.controller;

import com.unive.spsproject.model.Query1ResultDto;
import com.unive.spsproject.model.Query2ResultDto;
import com.unive.spsproject.model.Query3ResultDto;
import com.unive.spsproject.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    /*@Operation(summary = "Create flight")
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
    }*/


    Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Operation(summary = "Query 1")
    @GetMapping(value = "/query-1",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Query1ResultDto>> query1Controller(
            @RequestParam("flightDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate flightDate,
            @RequestParam("flightNumber") @NotNull Integer flightNumber, HttpServletRequest request
            ) {
        String s = httpServletRequestToString(request);

        logger.info(s);

        List<Query1ResultDto> result = flightService.query1(flightDate, flightNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Query 2")
    @GetMapping(value = "/query-2",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Query2ResultDto>> query2Controller(
            @RequestParam("lowerDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate lowerDate,
            @RequestParam("upperDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate upperDate,
            @RequestParam("minDelay") @NotNull Double minDelay, HttpServletRequest request
    ) {
        String s = httpServletRequestToString(request);

        logger.info(s);

        List<Query2ResultDto> result = flightService.query2(lowerDate, upperDate, minDelay);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Query 3")
    @GetMapping(value = "/query-3",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Query3ResultDto>> query3Controller(
            @RequestParam("lowerDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate lowerDate,
            @RequestParam("upperDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate upperDate,
            @RequestParam("numReturn") @NotNull Integer numReturn, HttpServletRequest request
    ) {
        String s = httpServletRequestToString(request);

        logger.info(s);

        List<Query3ResultDto> result = flightService.query3(lowerDate, upperDate, numReturn);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private String httpServletRequestToString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [" + request.getMethod() + "], ");
        sb.append("Request URL Path = [" + request.getRequestURL() + "], ");

        String headers =
                Collections.list(request.getHeaderNames()).stream()
                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
                        .collect(Collectors.joining(", "));

        if (headers.isEmpty()) {
            sb.append("Request headers: NONE,");
        } else {
            sb.append("Request headers: ["+headers+"],");
        }

        String parameters =
                Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
                        .collect(Collectors.joining(", "));

        if (parameters.isEmpty()) {
            sb.append("Request parameters: NONE.");
        } else {
            sb.append("Request parameters: [" + parameters + "].");
        }

        return sb.toString();
    }

}
