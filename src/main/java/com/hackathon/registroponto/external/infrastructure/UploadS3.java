package com.hackathon.registroponto.external.infrastructure;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class UploadS3 {

    @Autowired
    private AmazonS3 amazonS3;

    public void process(ByteArrayOutputStream file, String metaData) throws Exception {

        log.info("Inicio");

        log.info("Uploading ...");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.toByteArray());

        if(metaData == null)
            metaData = "email@teste.com";

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("pigeon", metaData);

        amazonS3.putObject(new PutObjectRequest("pigeon-bucket-hacka-app", "relatorios/"+ UUID.randomUUID() +"-"+ LocalDateTime.now() +".pdf",
                byteArrayInputStream, metadata));

        log.info("Fim do upload ...");

    }
}
