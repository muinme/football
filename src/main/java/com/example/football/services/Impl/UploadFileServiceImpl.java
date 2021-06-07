package com.example.football.services.Impl;

import com.example.football.form.UploadForm;
import com.example.football.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties("app")
public class UploadFileServiceImpl {

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UploadFileServiceImpl.class.getSimpleName());
    // read in configuration
    // read in configuration
    @Value("${app.upload_folder}")
    private String uploadFolder;


    public Map<String, Object> upload(HttpServletRequest request, UploadForm uploadForm) {
        LOG.info("UploadFile_Logic -> upload(HttpServletRequest, Model, UploadForm) -> is called");
        String parent = uploadForm.getParent();
        MultipartFile[] fileDatas = uploadForm.getFileDatas();
        if ((null == fileDatas) || (fileDatas.length == 0)) {
            return null;
        }
        List<File> uploadFiles = new ArrayList<File>();
        List<String> failedFiles = new ArrayList<String>();
        List<String> paths = new ArrayList<String>();
        String fullPath = "";
        String fileName = "";
        File file = null;
        for (MultipartFile fileData : fileDatas) {
            try {
                fileName = fileData.getOriginalFilename();
                fullPath = this.uploadFile(fileName, parent);
                paths.add(fullPath);
                file = new File(fullPath);
                if (!file.getParentFile().exists()) {
                    LOG.info("Parent directory " + file.getParentFile().getName() + " contain file " + fileName
                            + " is not exists -> create them");
                    file.getParentFile().mkdirs();
                }
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(fileData.getBytes());
                stream.close();
                uploadFiles.add(file);
            } catch (Exception e) {
                e.printStackTrace();
                failedFiles.add(fileName);
            }
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("uploadFiles", uploadFiles);
        msg.put("metaData", uploadForm.getMetaData());
        msg.put("failedFiles", failedFiles);
        msg.put("paths", paths);
        return msg;
    }

    public String uploadFile(String fileName, String parent, String prefix) {
        String fullPath = this.uploadFolder;
        System.out.println("paran " + parent);
        if (null != parent) {
            fullPath += File.separator + parent;
        }
        System.out.println("prefix " + prefix);
        if (null != prefix) {
            fullPath += File.separator + prefix;
        }
        fullPath += File.separator + fileName;

        System.out.println("File" + fileName);
        fullPath = this.checkFileName(fullPath);


        return fullPath;
    }

    public String uploadFile(String fileName, String parent) {
        return this.uploadFile(fileName, parent, null);
    }

    public String uploadFile(String fileName) {
        return this.uploadFile(fileName, null);
    }

    private String checkFileName(String fullPath) {
        // read in configuration
        String isUsingHdfs = "false";
        final boolean IS_USING_HDFS = Boolean.parseBoolean(isUsingHdfs);
        boolean isExsist = true;
        String fullPathResult = fullPath;
        try {
            int index = 0;
            while (isExsist) {
                // if using hdfs
                if (IS_USING_HDFS) {

                } else {
                    // else not using hdfs
                    File file = new File(fullPathResult);
                    isExsist = file.exists();
                }
                if (isExsist) {
                    // fix file name
                    LOG.info("UploadFile_Logic -> checkFileName(String) -> File is exists -> auto get new name");
                    int lastIndex = fullPath.lastIndexOf(".");
                    if (-1 == lastIndex) {
                        LOG.info(
                                "UploadFile_Logic -> checkFileName(String) -> File have no extentsion -> auto get new name");
                        lastIndex = fullPath.length() - 1;
                    }
                    fullPathResult = fullPath.substring(0, lastIndex) + "_" + index++ + fullPath.substring(lastIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        LOG.info("Origin path = " + fullPath);
        LOG.info("New path = " + fullPathResult);
        return fullPathResult;
    }



}


