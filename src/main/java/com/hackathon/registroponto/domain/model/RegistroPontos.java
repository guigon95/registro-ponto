package com.hackathon.registroponto.domain.model;

import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class RegistroPontos {

    public LocalDate dataRegistro;
    public List<RegistroPonto> registroPontoList;
    public BigDecimal horasTrabalhadas;

    public BigDecimal getHorasTrabalhadas() {

        this.horasTrabalhadas = BigDecimal.ZERO;

        if (registroPontoList.size() == 1)
            return this.horasTrabalhadas;

        for(int i =0; i<=registroPontoList.size()/2; i++) {

            Duration between = Duration.between(registroPontoList.get(i).getDataHoraPonto(), registroPontoList.get(i + 1).getDataHoraPonto());

            BigDecimal bigDecimal = BigDecimal.valueOf(between.getSeconds());

            BigDecimal horas = bigDecimal.divide(BigDecimal.valueOf(3600), 4, RoundingMode.FLOOR);

            this.horasTrabalhadas = this.horasTrabalhadas.add(horas);

            i++;

        }

        return this.horasTrabalhadas;
    }
}
