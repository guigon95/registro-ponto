package com.hackathon.registroponto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistroPontoRequest implements Serializable {

    @JsonProperty("funcionarioId")
    public UUID funcionarioId;
}
