package com.hackathon.registroponto.adapter.gateway;

import com.hackathon.registroponto.adapter.dto.RelatorioResponse;
import com.hackathon.registroponto.domain.model.Relatorio;
import com.hackathon.registroponto.external.gateway.PdfGateway;
import com.hackathon.registroponto.external.infrastructure.PdfUtils;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
@RequiredArgsConstructor
public class PdfGatewayImpl implements PdfGateway {

    private final PdfUtils pdfUtils;

    @Override
    public ByteArrayOutputStream generatePdfStream(Relatorio data) throws DocumentException {
        return pdfUtils.generatePdfStream(data);
    }
}
