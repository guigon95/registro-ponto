package com.hackathon.registroponto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistroPonto {

    private UUID id;

    private LocalDateTime dataHoraPonto;

    private UUID funcionarioId;

    public LocalDate getData(){
        return dataHoraPonto.toLocalDate();
    }
}
