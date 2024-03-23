package com.hackathon.registroponto.adapter.gateway;

import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import com.hackathon.registroponto.external.infrastructure.repository.jpa.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<RegistroPonto> obterRegistros(ObterRegistros obterRegistros) {

        RegistroPontoEntity build = RegistroPontoEntity.builder()
                .funcionarioId(obterRegistros.getFuncionarioId())
                .build();


        List<RegistroPontoEntity> all = registroPontoRepository.findAll(Example.of(build), Sort.by(Sort.Direction.ASC, "funcionarioId"));


        return all.stream().map(registroPontoMapper::registroPontoEntityToRegistroPonto).toList();
    }
}
