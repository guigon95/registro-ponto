package com.hackathon.registroponto.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ObterRegistrosRequest {

    UUID funcionarioId;

    LocalDate dataPonto;
}
