package com.hackathon.registroponto.adapter.gateway;

import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import com.hackathon.registroponto.external.infrastructure.repository.jpa.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RegistroPontoGatewayImpl implements RegistroPontoGateway {

    private final RegistroPontoRepository registroPontoRepository;
    private final RegistroPontoMapper registroPontoMapper;

    @Override
    public RegistroPonto registrar(RegistroPonto registroPonto) {
        var registroPontoEntity = registroPontoMapper.registroPontoToRegegistroPontoEntity(registroPonto);
        return registroPontoMapper.registroPontoEntityToRegistroPonto(registroPontoRepository.save(registroPontoEntity));
    }

    @Override
    public boolean pontoJaFoiRegistrado(RegistroPonto registroPonto) {

        Optional<RegistroPontoEntity> registroPontoEntity = registroPontoRepository.findByDataPontoAndTipoRegistroAndFuncionarioId(registroPonto.getDataPonto(), registroPonto.getTipoRegistro(), registroPonto.getFuncionarioId());

        if(registroPontoEntity.isPresent())
            return true;

        return false;
    }
}
