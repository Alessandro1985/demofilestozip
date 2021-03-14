package com.example.demofilestozip.resource

import com.example.demofilestozip.model.CreatedZip
import com.example.demofilestozip.service.ZipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

import javax.ws.rs.core.Response
import java.util.zip.ZipOutputStream

class FileToZipResourceSpec extends Specification {

    def fileToZipResource
    static CreatedZip createdZipMock
    static ZipOutputStream zipOutputStreamMock
    static MultipartFile multipartFileMock

    @Autowired
    ZipService zipServiceMock

    def setup(){
        zipServiceMock = Mock()
        createdZipMock = Mock()
        zipOutputStreamMock = Mock()
        multipartFileMock = Mock()
        fileToZipResource = new FileToZipResource()
    }

    def "addFilesToZip returns a zip with 3 files"(){
        given: "the ZipService returns a zip with 3 files"
            createdZipMock.setZipName("zip")
            createdZipMock.setZipFile(zipOutputStreamMock)
            zipServiceMock.getZipOutputStream(createFilesList()) >> createdZipMock

        when: "3 files are sent to the /addToZip endpoint"
            Response resp = fileToZipResource.addFilesToZip(multipartFileMock, multipartFileMock, multipartFileMock)

        then: "Ok response is returned"
            resp.status == HttpStatus.OK.value()
    }

    private List<MultipartFile> createFilesList() {
        List<MultipartFile> multipartFileList
        MultipartFile multipartFileMock = Mock()
        1.upto(3){
            multipartFileList.add(multipartFileMock)
        }
        multipartFileList
    }


}
