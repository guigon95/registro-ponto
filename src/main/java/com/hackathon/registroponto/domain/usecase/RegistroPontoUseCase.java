package com.hackathon.registroponto.domain.usecase;

import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RegistroPontoUseCase {
    
    RegistroPonto registrarPonto(RegistroPonto registroPonto);

    Map<LocalDate, List<RegistroPonto>> obterRegistros(ObterRegistros obterRegistros);
}
