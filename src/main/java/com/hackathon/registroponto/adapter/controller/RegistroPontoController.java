package com.hackathon.registroponto.adapter.controller;

import com.hackathon.registroponto.adapter.dto.ObterRegistrosRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import com.hackathon.registroponto.adapter.dto.RegistroPontoResponses;
import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoMapper registroPontoMapper;
    private final RegistroPontoUseCase registroPontoUseCase;

    public ResponseEntity<RegistroPontoResponse> registrarPonto(RegistroPontoRequest registroPontoRequest){
        var registroPonto = registroPontoMapper.registroPontoRequestToRegistroPonto(registroPontoRequest);

        return ResponseEntity.ok(registroPontoMapper.registroPontoToRegistroPontoResponse(registroPontoUseCase.registrarPonto(registroPonto)));

    }

    public List<RegistroPontoResponses> obterRegistros(ObterRegistrosRequest obterRegistrosRequest){
        var obterRegistros = registroPontoMapper.obterRegistrosRequestToObterRegistros(obterRegistrosRequest);


        var registrosByData = registroPontoUseCase.obterRegistros(obterRegistros);

        List<RegistroPontoResponses> lista = new ArrayList<>();
        registrosByData.forEach((data, registroPontos) -> {
            var responses = RegistroPontoResponses.builder().dataRegistro(data);
            List<RegistroPontoResponse> list = registroPontos
                    .stream()
                    .map(registroPontoMapper::registroPontoToRegistroPontoResponse)
                    //
                    .toList();

            responses.registroPontoResponse(list);


            lista.add(responses.build());
        });

        return lista.stream().sorted(Comparator.comparing(RegistroPontoResponses::getDataRegistro)).toList();

    }
}
