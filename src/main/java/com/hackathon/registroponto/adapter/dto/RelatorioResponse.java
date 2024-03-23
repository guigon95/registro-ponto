package com.hackathon.registroponto.adapter.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class RelatorioResponse {

    public List<RegistroPontosResponse> registroPontos;

    private BigDecimal totalHorasTrabalhadas;
}
