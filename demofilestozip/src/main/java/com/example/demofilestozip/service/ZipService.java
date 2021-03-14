package com.example.demofilestozip.service;

import com.example.demofilestozip.model.CreatedZip;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipService {

    public CreatedZip getZipOutputStream(List<MultipartFile> files) throws IOException {
        FileOutputStream fos = new FileOutputStream("multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (MultipartFile file: files) {
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOut.putNextEntry(zipEntry);

            InputStream inputStream =file.getInputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            inputStream.close();
        }
        zipOut.close();
        fos.close();
        CreatedZip zipEntity = new CreatedZip();
        zipEntity.setZipFile(zipOut);
        zipEntity.setZipName("zip");
        return zipEntity;
    }


}
