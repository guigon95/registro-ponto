package com.hackathon.registroponto.adapter.dto;

import com.hackathon.registroponto.domain.TipoRegistro;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class RegistroPontoRequest {

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;

    private UUID funcionarioId;
}
