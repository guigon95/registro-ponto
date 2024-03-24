package com.hackathon.registroponto.external.infrastructure;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;

@Component
@Slf4j
public class UploadS3 {

    @Autowired
    private AmazonS3 amazonS3;

    public void process(ByteArrayOutputStream file) throws Exception {

        log.info("Inicio");

        log.info("Uploading ...");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.toByteArray());

        amazonS3.putObject(new PutObjectRequest("pigeon-bucket-hacka-app", "relatorios/relatorio-"+ LocalDateTime.now() +".pdf",
                byteArrayInputStream, null));

        log.info("Fim do upload ...");

    }
}
