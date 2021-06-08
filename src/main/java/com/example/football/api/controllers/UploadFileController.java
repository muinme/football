package com.example.football.api.controllers;

import com.example.football.form.UploadForm;
import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.services.Impl.UploadFileServiceImpl;
import com.example.football.services.PitchService;
import com.example.football.services.TeamFootBallService;
import com.example.football.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@ConfigurationProperties("app")
public class UploadFileController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadFileController.class.getSimpleName());
    @Autowired
    private UploadFileServiceImpl service;
    @Value("${app.syncOptionsImportFile}")
    private String syncOptionsImportFile;

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private UserService userService;

    @Autowired
    private TeamFootBallService teamFootBallService;

    @Autowired
    private PitchService pitchService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    // @PostMapping(value = { "/admin/sync/options/importfile" }, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = {
    "/admin/sync/options/importfile" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Object, Object>> importFile(HttpServletRequest request,
            @ModelAttribute("uploadForm") UploadForm uploadForm) {
        // call upload file, get file paths output -> insert to database table data_synchronization_history
        // - status 0

        String jwt = cookieUtil.getValue(request, jwtTokenCookieName);
        System.out.println(jwt);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        Map<Object, Object> result = new HashMap<>();
        try {
            LOG.info("SyncBlackListController -> importfile -> is called");
            uploadForm.setParent(this.syncOptionsImportFile);


            if ((null == uploadForm.getFileDatas()) || (0 == uploadForm.getFileDatas().length)
                    || uploadForm.getFileDatas()[0].getOriginalFilename().isEmpty()) {
                LOG.info("UploadFileController -> Not found file is upload");
                result.put("message", "error");
                result.put("status", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

            Map<String, Object> data = this.service.upload(request, uploadForm);
            // String metaData = (String) data.get("metaData");
            // List<File> uploadFiles = (List<File>) data.get("uploadFiles");
            // List<String> failedFiles = (List<String>) data.get("failedFiles");
            @SuppressWarnings("unchecked")
            List<String> paths = (List<String>) data.get("paths");
            String path = paths.get(0);
            userService.updateLoadAvatar(path, username);

            result.put("message", "successfull");
            result.put("status", 200);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("LOI CMNR BAN OI");
            result.put("message", "error");
            result.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @RequestMapping(value = {
            "/admin/sync/options/importfileImageTeam/{id}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Object, Object>> importFileImageTeam(@PathVariable Integer id, HttpServletRequest request,
                                                          @ModelAttribute("uploadForm") UploadForm uploadForm) {
        // call upload file, get file paths output -> insert to database table data_synchronization_history
        // - status 0

        String jwt = cookieUtil.getValue(request, jwtTokenCookieName);
        System.out.println(jwt);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        Map<Object, Object> result = new HashMap<>();
        try {
            LOG.info("SyncBlackListController -> importfile -> is called");
            uploadForm.setParent(this.syncOptionsImportFile);


            if ((null == uploadForm.getFileDatas()) || (0 == uploadForm.getFileDatas().length)
                    || uploadForm.getFileDatas()[0].getOriginalFilename().isEmpty()) {
                LOG.info("UploadFileController -> Not found file is upload");
                result.put("message", "error");
                result.put("status", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

            Map<String, Object> data = this.service.upload(request, uploadForm);
            // String metaData = (String) data.get("metaData");
            // List<File> uploadFiles = (List<File>) data.get("uploadFiles");
            // List<String> failedFiles = (List<String>) data.get("failedFiles");
            @SuppressWarnings("unchecked")
            List<String> paths = (List<String>) data.get("paths");
            String path = paths.get(0);
            teamFootBallService.updateLoadAvatarTeam(path, id);

            result.put("message", "successfull");
            result.put("status", 200);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("LOI CMNR BAN OI");
            result.put("message", "error");
            result.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @RequestMapping(value = {
            "/admin/sync/options/importfileLogoTeam/{id}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Object, Object>> importFileLogoTeam(@PathVariable Integer id, HttpServletRequest request,
                                                                   @ModelAttribute("uploadForm") UploadForm uploadForm) {
        // call upload file, get file paths output -> insert to database table data_synchronization_history
        // - status 0

        Map<Object, Object> result = new HashMap<>();
        try {
            LOG.info("SyncBlackListController -> importfile -> is called");
            uploadForm.setParent(this.syncOptionsImportFile);


            if ((null == uploadForm.getFileDatas()) || (0 == uploadForm.getFileDatas().length)
                    || uploadForm.getFileDatas()[0].getOriginalFilename().isEmpty()) {
                LOG.info("UploadFileController -> Not found file is upload");
                result.put("message", "error");
                result.put("status", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

            Map<String, Object> data = this.service.upload(request, uploadForm);
            // String metaData = (String) data.get("metaData");
            // List<File> uploadFiles = (List<File>) data.get("uploadFiles");
            // List<String> failedFiles = (List<String>) data.get("failedFiles");
            @SuppressWarnings("unchecked")
            List<String> paths = (List<String>) data.get("paths");
            String path = paths.get(0);
            teamFootBallService.updateLoadLogoTeam(path, id);

            result.put("message", "successfull");
            result.put("status", 200);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("LOI CMNR BAN OI");
            result.put("message", "error");
            result.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @RequestMapping(value = {
            "/admin/sync/options/importfileImagePitch/{id}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Object, Object>> importFileImagePitch(@PathVariable Integer id, HttpServletRequest request,
                                                                  @ModelAttribute("uploadForm") UploadForm uploadForm) {
        // call upload file, get file paths output -> insert to database table data_synchronization_history
        // - status 0

        Map<Object, Object> result = new HashMap<>();
        try {
            LOG.info("SyncBlackListController -> importfile -> is called");
            uploadForm.setParent(this.syncOptionsImportFile);


            if ((null == uploadForm.getFileDatas()) || (0 == uploadForm.getFileDatas().length)
                    || uploadForm.getFileDatas()[0].getOriginalFilename().isEmpty()) {
                LOG.info("UploadFileController -> Not found file is upload");
                result.put("message", "error");
                result.put("status", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

            Map<String, Object> data = this.service.upload(request, uploadForm);
            // String metaData = (String) data.get("metaData");
            // List<File> uploadFiles = (List<File>) data.get("uploadFiles");
            // List<String> failedFiles = (List<String>) data.get("failedFiles");
            @SuppressWarnings("unchecked")
            List<String> paths = (List<String>) data.get("paths");
            String path = paths.get(0);
            pitchService.updateLoadAvatarPitch(path, id);

            result.put("message", "successfull");
            result.put("status", 200);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("LOI CMNR BAN OI");
            result.put("message", "error");
            result.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
