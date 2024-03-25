package com.hackathon.registroponto.adapter.controller;

import com.hackathon.registroponto.adapter.dto.*;
import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.hackathon.registroponto.external.infrastructure.UploadS3;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoMapper registroPontoMapper;
    private final RegistroPontoUseCase registroPontoUseCase;
    private final UploadS3 uploadS3;

    public RegistroPontoResponse registrarPonto(RegistroPontoRequest registroPontoRequest){
        var registroPonto = registroPontoMapper.registroPontoRequestToRegistroPonto(registroPontoRequest);

        return registroPontoMapper.registroPontoToRegistroPontoResponse(registroPontoUseCase.registrarPonto(registroPonto));

    }

    public RelatorioResponse obterRegistros(ObterRegistrosRequest obterRegistrosRequest){
        var obterRegistros = registroPontoMapper.obterRegistrosRequestToObterRegistros(obterRegistrosRequest);


        var registrosByData = registroPontoUseCase.obterRegistros(obterRegistros);

        return registroPontoMapper.relatorioToRelatorioResponse(registrosByData);

    }

    public ByteArrayOutputStream gerarRelatorio(UUID funcionarioId, String header) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = registroPontoUseCase.gerarRelatorio(funcionarioId);

        uploadS3.process(byteArrayOutputStream, header);

        return byteArrayOutputStream;
    }
}
