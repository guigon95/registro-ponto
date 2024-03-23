package com.hackathon.registroponto.external.infrastructure.repository.jpa;

import com.hackathon.registroponto.domain.TipoRegistro;
import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface RegistroPontoRepository extends JpaRepository<RegistroPontoEntity, UUID> {

    Optional<RegistroPontoEntity> findByDataPontoAndTipoRegistroAndFuncionarioId(LocalDate dataPonto, TipoRegistro tipoRegistro, UUID funcionarioId);
}
