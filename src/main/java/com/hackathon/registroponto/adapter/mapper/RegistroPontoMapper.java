package com.hackathon.registroponto.adapter.mapper;

import com.hackathon.registroponto.adapter.dto.ObterRegistrosRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import com.hackathon.registroponto.domain.model.ObterRegistros;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.external.infrastructure.entity.RegistroPontoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistroPontoMapper {

    RegistroPonto registroPontoEntityToRegistroPonto(RegistroPontoEntity registroPontoEntity);

    RegistroPontoEntity registroPontoToRegegistroPontoEntity(RegistroPonto registroPonto);

    RegistroPonto registroPontoRequestToRegistroPonto(RegistroPontoRequest registroPontoRequest);

    RegistroPontoResponse registroPontoToRegistroPontoResponse(RegistroPonto registroPonto);


    ObterRegistros obterRegistrosRequestToObterRegistros(ObterRegistrosRequest obterRegistrosRequest);
}
