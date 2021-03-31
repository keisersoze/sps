package com.unive.spsproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Query3ResultDto {

    @JsonProperty
    private Integer originAirportId;

    public Query3ResultDto(Integer originAirportId) {
        this.originAirportId = originAirportId;
    }

    public Integer getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(Integer originAirportId) {
        this.originAirportId = originAirportId;
    }
}
