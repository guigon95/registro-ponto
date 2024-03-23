package com.hackathon.registroponto.application.core.exceptions;

import com.hackathon.registroponto.adapter.dto.exceptions.ErrorMessage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InvalidFieldException extends RuntimeException {
    String message;
    List<ErrorMessage.CauseError> causeErrorList;
}
