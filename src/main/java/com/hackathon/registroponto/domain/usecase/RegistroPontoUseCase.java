package com.hackathon.registroponto.domain.usecase;

import com.hackathon.registroponto.adapter.dto.RelatorioResponse;
import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.model.Relatorio;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public interface RegistroPontoUseCase {
    
    RegistroPonto registrarPonto(RegistroPonto registroPonto);

    Relatorio obterRegistros(ObterRegistros obterRegistros);

    ByteArrayOutputStream gerarRelatorio(UUID funcionarioId) throws DocumentException;
}
