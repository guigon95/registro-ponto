package com.hackathon.registroponto.adapter.controller;

import com.hackathon.registroponto.adapter.dto.RegistroPontoRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import com.hackathon.registroponto.adapter.mapper.RegistroPontoMapper;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoMapper registroPontoMapper;
    private final RegistroPontoUseCase registroPontoUseCase;

    public ResponseEntity<RegistroPontoResponse> registrarPonto(RegistroPontoRequest registroPontoRequest){
        var registroPonto = registroPontoMapper.registroPontoRequestToRegistroPonto(registroPontoRequest);
        return ResponseEntity.ok(registroPontoMapper.productToProductResponse(registroPontoUseCase.registrarPonto(registroPonto)));

    }
}
