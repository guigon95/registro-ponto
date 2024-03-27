package com.hackathon.registroponto.external.api;

import com.hackathon.registroponto.adapter.controller.RegistroPontoController;
import com.hackathon.registroponto.adapter.dto.ObterRegistrosRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoRequest;
import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import com.hackathon.registroponto.adapter.dto.RelatorioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(registroPontoController.registrarPonto(registroPontoRequest));
    }


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar pontos do mes anterior")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pontos registrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RelatorioResponse.class)) }),
            @ApiResponse(responseCode = "4xx", description = "Invalid data",
                    content = @Content),
            @ApiResponse(responseCode = "5xx", description = "Internal server error",
                    content = @Content) })
    @GetMapping
    public RelatorioResponse registrar(@RequestBody @Valid ObterRegistrosRequest obterRegistrosRequest) {
        return registroPontoController.obterRegistros(obterRegistrosRequest);
    }

    @PostMapping("/pdf")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Solicitar envio do pdf por email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio do pdf por email",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RelatorioResponse.class)) }),
            @ApiResponse(responseCode = "4xx", description = "Invalid data",
                    content = @Content),
            @ApiResponse(responseCode = "5xx", description = "Internal server error",
                    content = @Content) })
    public ResponseEntity<String> exportPdf(@RequestBody ObterRegistrosRequest request, HttpServletRequest httpServletRequest) throws Exception {

        String header = httpServletRequest.getHeader("x-amz-meta-pigeon");

        ByteArrayOutputStream pdfStream = registroPontoController.gerarRelatorio(request.getFuncionarioId(), header);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registros_pontos.pdf");
        headers.setContentLength(pdfStream.size());

        return ResponseEntity.ok("Pdf foi enviado ao email "+ header);
    }
}
