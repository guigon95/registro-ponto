package com.hackathon.registroponto.domain.model;

import com.hackathon.registroponto.domain.TipoRegistro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistroPonto {

    private UUID id;

    private LocalDate dataPonto;

    private LocalTime horaPonto;

    private TipoRegistro tipoRegistro;

    private UUID funcionarioId;
}
