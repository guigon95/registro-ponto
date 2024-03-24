package com.hackathon.registroponto.external.api;

import com.hackathon.registroponto.adapter.controller.RegistroPontoController;
import com.hackathon.registroponto.adapter.dto.*;
import com.hackathon.registroponto.domain.usecase.RegistroPontoUseCase;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/registro-ponto")
@RequiredArgsConstructor
@Tag(name = "registros", description = "Acesso aos registros de ponto")
public class RegistroPontoApi {

    private final RegistroPontoController registroPontoController;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar um ponto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ponto registrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegistroPontoResponse.class)) }),
            @ApiResponse(responseCode = "4xx", description = "Invalid data",
                    content = @Content),
            @ApiResponse(responseCode = "5xx", description = "Internal server error",
                    content = @Content) })
    public ResponseEntity<RegistroPontoResponse> registrar(@RequestBody @Valid RegistroPontoRequest registroPontoRequest) {
        return registroPontoController.registrarPonto(registroPontoRequest);
    }

    @GetMapping
    public RelatorioResponse registrar(@RequestBody @Valid ObterRegistrosRequest obterRegistrosRequest) {
        return registroPontoController.obterRegistros(obterRegistrosRequest);
    }

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestBody ObterRegistrosRequest request, HttpServletRequest httpServletRequest) throws Exception {

        String header = httpServletRequest.getHeader("x-amz-meta-pigeon");

        ByteArrayOutputStream pdfStream = registroPontoController.gerarRelatorio(request.getFuncionarioId(), header);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registros_pontos.pdf");
        headers.setContentLength(pdfStream.size());

        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}
