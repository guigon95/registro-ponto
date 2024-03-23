package com.hackathon.registroponto.external.infrastructure.repository.jpa;

import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RegistroPontoRepository extends JpaRepository<RegistroPontoEntity, UUID> {
    List<RegistroPontoEntity> findByFuncionarioIdAndDataHoraPontoGreaterThanEqualAndDataHoraPontoLessThanEqual(UUID funcionarioId, LocalDateTime dataInicio, LocalDateTime dataFim, Sort funcionarioId1);
}
