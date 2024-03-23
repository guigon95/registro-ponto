package com.hackathon.registroponto.adapter.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class RegistroPontoResponse {

    private LocalDate dataPonto;

    private LocalTime horaPonto;

    private UUID funcionarioId;
}
