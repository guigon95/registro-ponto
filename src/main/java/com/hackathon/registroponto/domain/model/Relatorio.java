package com.hackathon.registroponto.domain.model;

import com.hackathon.registroponto.adapter.dto.RegistroPontosResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class Relatorio {

    public List<RegistroPontos> registroPontos;

    private BigDecimal totalHorasTrabalhadas;

    public BigDecimal getTotalHorasTrabalhadas(){
        this.totalHorasTrabalhadas =  registroPontos.stream()
                .map(RegistroPontos::getHorasTrabalhadas)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalHorasTrabalhadas;
    }
}
