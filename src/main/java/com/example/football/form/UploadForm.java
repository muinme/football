package com.example.football.form;


import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
    //@NotEmpty(message = "Must be choose least a file")

    private MultipartFile[] fileDatas;
    private String parent;
    private String metaData;

    public MultipartFile[] getFileDatas() {
        return this.fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getMetaData() {
        return this.metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

}
