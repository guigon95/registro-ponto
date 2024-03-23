package com.hackathon.registroponto.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ObterRegistros {

    UUID funcionarioId;

    LocalDate dataPonto;
}
