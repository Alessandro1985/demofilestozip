package com.example.demofilestozip.model;

import lombok.Data;

import java.util.zip.ZipOutputStream;

@Data
public class CreatedZip {
    public String zipName;
    public ZipOutputStream zipFile;
}
