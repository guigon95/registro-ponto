package com.hackathon.registroponto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class RegistroPontosResponse {

    @JsonProperty("data_registro")
    public LocalDate dataRegistro;
    @JsonProperty("registros")
    public List<RegistroPontoResponse> registroPontoList;
    @JsonProperty("horas_trabalhadas")
    public BigDecimal horasTrabalhadas;
}
