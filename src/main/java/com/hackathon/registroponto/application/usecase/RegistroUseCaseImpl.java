package com.hackathon.registroponto.application.usecase;

import com.hackathon.registroponto.application.core.exceptions.PontoJaRegistradoException;
import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistroUseCaseImpl implements RegistroPontoUseCase {

    private final RegistroPontoGateway registroPontoGateway;

    @Override
    public RegistroPonto registrarPonto(RegistroPonto registroPonto) {

        registroPonto.setDataHoraPonto(LocalDateTime.now(ZoneOffset.UTC));

        return registroPontoGateway.registrar(registroPonto);
    }

    @Override
    public Map<LocalDate, List<RegistroPonto>> obterRegistros(ObterRegistros obterRegistros) {

        List<RegistroPonto> registroPontos = registroPontoGateway.obterRegistros(obterRegistros);

        Map<LocalDate, List<RegistroPonto>> registroPontoMap = registroPontos.stream()
                .collect(Collectors.groupingBy(RegistroPonto::getData));

        return registroPontoMap;
    }
}
