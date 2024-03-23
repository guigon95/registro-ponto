package com.hackathon.registroponto.external.infrastructure.entity;

import com.hackathon.registroponto.domain.TipoRegistro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistroPontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate dataPonto;

    private LocalTime horaPonto;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;

    private UUID funcionarioId;

}
