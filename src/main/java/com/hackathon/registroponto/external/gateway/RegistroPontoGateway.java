package com.hackathon.registroponto.external.gateway;

import com.hackathon.registroponto.domain.model.RegistroPonto;

public interface RegistroPontoGateway {

    RegistroPonto registrar(RegistroPonto registroPonto);
}
