package com.hackathon.registroponto.application.usecase;

import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroUseCaseImpl implements RegistroPontoUseCase {

    private final RegistroPontoGateway registroPontoGateway;

    @Override
    public RegistroPonto registrarPonto(RegistroPonto registroPonto) {
        return registroPontoGateway.registrar(registroPonto);
    }
}
