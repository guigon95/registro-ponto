package com.hackathon.registroponto.external.gateway;

import com.hackathon.registroponto.adapter.dto.RelatorioResponse;
import com.hackathon.registroponto.domain.model.Relatorio;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;

public interface PdfGateway {
    ByteArrayOutputStream generatePdfStream(Relatorio datas) throws DocumentException;

}
