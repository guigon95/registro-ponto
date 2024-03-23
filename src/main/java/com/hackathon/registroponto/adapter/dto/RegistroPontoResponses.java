package com.hackathon.registroponto.adapter.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class RegistroPontoResponses {

    public LocalDate dataRegistro;
    public List<RegistroPontoResponse> registroPontoResponse;

}
