package com.hackathon.registroponto.application.core.exceptions;

import com.hackathon.registroponto.adapter.dto.exceptions.ErrorMessage;

import java.util.List;

public class PontoJaRegistradoException extends RuntimeException {

    public PontoJaRegistradoException(){
        super("Ponto ja registrado");
    }
    String message;
    List<ErrorMessage.CauseError> causeErrorList;
}
