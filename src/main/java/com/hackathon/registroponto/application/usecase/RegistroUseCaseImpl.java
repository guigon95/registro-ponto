package com.hackathon.registroponto.application.usecase;

import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.model.RegistroPontos;
import com.hackathon.registroponto.domain.model.Relatorio;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.hackathon.registroponto.external.gateway.PdfGateway;
import com.hackathon.registroponto.external.gateway.RegistroPontoGateway;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistroUseCaseImpl implements RegistroPontoUseCase {

    private final RegistroPontoGateway registroPontoGateway;
    private final PdfGateway pdfGateway;
    private final RegistroPontoMapper registroPontoMapper;

    @Override
    public RegistroPonto registrarPonto(RegistroPonto registroPonto) {

        registroPonto.setDataHoraPonto(LocalDateTime.now(ZoneOffset.UTC));

        return registroPontoGateway.registrar(registroPonto);
    }

    @Override
    public Relatorio obterRegistros(ObterRegistros obterRegistros) {

        LocalDateTime now = LocalDateTime.now();

        var dataInicio = LocalDateTime.of(now.getYear(), now.getMonth().minus(1), 1, 0,0,0);
        var dataFim = dataInicio.with(TemporalAdjusters.lastDayOfMonth()).plusHours(23).plusMinutes(59).plusSeconds(59);

        obterRegistros.setDataInicio(dataInicio);
        obterRegistros.setDataFim(dataFim);

        List<RegistroPonto> registroPontoList = registroPontoGateway.obterRegistros(obterRegistros);

        return Relatorio.builder().registroPontos(registroPontoList.stream().collect(Collectors.groupingBy(RegistroPonto::getData))
                .entrySet().stream()
                .map(entry -> RegistroPontos.builder()
                        .dataRegistro(entry.getKey())
                        .registroPontoList(new ArrayList<>(entry.getValue()))
                        .build())
                .collect(Collectors.toList())).build();

    }

    @Override
    public ByteArrayOutputStream gerarRelatorio(UUID funcionarioId) throws DocumentException {
        return pdfGateway.generatePdfStream(obterRegistros(ObterRegistros.builder().funcionarioId(funcionarioId).build()));
    }
}
