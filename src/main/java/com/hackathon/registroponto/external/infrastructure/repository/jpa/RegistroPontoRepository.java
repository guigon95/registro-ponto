package com.hackathon.registroponto.external.infrastructure.repository.jpa;

import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroPontoRepository extends JpaRepository<RegistroPontoEntity, UUID> {
}
