package com.hackathon.registroponto.external.infrastructure.entity;

import com.hackathon.registroponto.domain.TipoRegistro;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegistroPontoEntity {

    private UUID id;
    private LocalDateTime dataHoraPonto;
    private TipoRegistro tipoRegistro;
    private UUID funcionarioId;

}
