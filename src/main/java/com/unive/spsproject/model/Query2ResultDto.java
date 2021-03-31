package com.unive.spsproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Query2ResultDto {

    // idk what is the id to return, so for the mockup we have choose incremental id that we have put on the creation of the DB
    @JsonProperty
    private long id;

    // idk what is the "data" to return, so for the mockup we have choose FL_DATE
    @JsonProperty
    private LocalDate flDate;

    @JsonProperty
    private String originCityName;

    @JsonProperty
    private String destCityName;

    public Query2ResultDto(long id, LocalDate flDate, String originCityName, String destCityName) {
        this.id = id;
        this.flDate = flDate;
        this.originCityName = originCityName;
        this.destCityName = destCityName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFlDate() {
        return flDate;
    }

    public void setFlDate(LocalDate flDate) {
        this.flDate = flDate;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public void setOriginCityName(String originCityName) {
        this.originCityName = originCityName;
    }

    public String getDestCityName() {
        return destCityName;
    }

    public void setDestCityName(String destCityName) {
        this.destCityName = destCityName;
    }
}
