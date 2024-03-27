package com.hackathon.registroponto.external.infrastructure;

import com.hackathon.registroponto.adapter.dto.RegistroPontoResponse;
import com.hackathon.registroponto.adapter.dto.RegistroPontosResponse;
import com.hackathon.registroponto.adapter.dto.RelatorioResponse;
import com.hackathon.registroponto.domain.model.RegistroPonto;
import com.hackathon.registroponto.domain.model.RegistroPontos;
import com.hackathon.registroponto.domain.model.Relatorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class PdfUtils {
    public ByteArrayOutputStream generatePdfStream(Relatorio data) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Escreva os nomes das colunas
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph titulo = new Paragraph("Registro de Ponto", boldFont);
        document.add(titulo);
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(2); // Cria uma tabela com 8 colunas
        table.addCell(createCell("Data", 1, 1, PdfPCell.BOX, Font.BOLD));
        table.addCell(createCell("Registro", 1, 1, PdfPCell.BOX, Font.BOLD));

        // Escreva as linhas de dados
        for (RegistroPontos registroPontos : data.registroPontos) {
            var colocaData = true;

            for (RegistroPonto registro : registroPontos.registroPontoList) {
                if(colocaData){
                    table.addCell(createCell(registro.getDataHoraPonto().toLocalDate().toString(), 1, 1, PdfPCell.BOX, Font.NORMAL));
                    colocaData = false;
                } else {
                    table.addCell(createCell("", 1, 1, PdfPCell.BOX, Font.NORMAL));
                }

                table.addCell(createCell(registro.getDataHoraPonto().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 1, 1, PdfPCell.BOX, Font.NORMAL));
             }

            table.addCell(createCell("Horas trabalhadas", 1, 1, PdfPCell.BOX, Font.BOLD));
            table.addCell(createCell(registroPontos.getHorasTrabalhadas().toString(), 1, 1, PdfPCell.BOX, Font.NORMAL));
        }

        document.add(table);
        document.add(new Paragraph("Total de Horas trabalhadas", boldFont));
        document.add(new Paragraph(data.getTotalHorasTrabalhadas().toString(), boldFont));
        document.close();
        return outputStream;
    }

    private PdfPCell createCell(String text, int colspan, int rowspan, int border, int style) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, new Font(Font.FontFamily.HELVETICA, 12, style)));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(border);
        return cell;
    }
}
