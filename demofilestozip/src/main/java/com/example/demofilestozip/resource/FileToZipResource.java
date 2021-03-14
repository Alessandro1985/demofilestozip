package com.example.demofilestozip.resource;

import com.example.demofilestozip.model.CreatedZip;
import com.example.demofilestozip.service.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileToZipResource {

    @Autowired
    ZipService zipService;

    @PostMapping("/addToZip")
    @Produces("application/zip")
    public Response addFilesToZip(@FormParam("file1") MultipartFile file1, @FormParam("file2") MultipartFile file2, @FormParam("file3") MultipartFile file3) throws IOException {

        List<MultipartFile> filesList = Arrays.asList(file1, file2, file3);
        CreatedZip createdZip = zipService.getZipOutputStream(filesList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        Response response = Response.ok().entity(createdZip).build();
        return response;
    }
}


