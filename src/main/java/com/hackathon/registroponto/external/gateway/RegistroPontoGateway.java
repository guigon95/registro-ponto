package com.hackathon.registroponto.external.gateway;

import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;

import java.util.List;

public interface RegistroPontoGateway {

    RegistroPonto registrar(RegistroPonto registroPonto);

    List<RegistroPonto> obterRegistros(ObterRegistros obterRegistros);
}
