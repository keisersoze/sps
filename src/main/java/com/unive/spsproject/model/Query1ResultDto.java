package com.unive.spsproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Query1ResultDto {

    @JsonProperty
    private Double arrivalDelay;

    @JsonProperty
    private Double departureDelay;

    public Query1ResultDto(Double arrivalDelay, Double departureDelay) {
        this.arrivalDelay = arrivalDelay;
        this.departureDelay = departureDelay;
    }

    public Double getArrivalDelay() {
        return arrivalDelay;
    }

    public void setArrivalDelay(Double arrivalDelay) {
        this.arrivalDelay = arrivalDelay;
    }

    public Double getDepartureDelay() {
        return departureDelay;
    }

    public void setDepartureDelay(Double departureDelay) {
        this.departureDelay = departureDelay;
    }
}
