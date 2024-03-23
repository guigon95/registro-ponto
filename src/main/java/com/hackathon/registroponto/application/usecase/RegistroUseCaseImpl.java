package com.hackathon.registroponto.application.usecase;

import com.hackathon.registroponto.application.core.exceptions.PontoJaRegistradoException;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RegistroUseCaseImpl implements RegistroPontoUseCase {

    private final RegistroPontoGateway registroPontoGateway;

    @Override
    public RegistroPonto registrarPonto(RegistroPonto registroPonto) {

        registroPonto.setDataPonto(LocalDate.now());

        boolean pontoJaFoiRegistrado = registroPontoGateway.pontoJaFoiRegistrado(registroPonto);

        if (pontoJaFoiRegistrado)
            throw new PontoJaRegistradoException();

        registroPonto.setHoraPonto(LocalTime.now());

        return registroPontoGateway.registrar(registroPonto);
    }
}
