package com.example.upload.demo.controller;

import com.example.upload.demo.domain.UploadFileResponse;
import com.example.upload.demo.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UploadFileService fileService;

    /**
     * 上传单个文件
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        //存储
        String fileName = fileService.storeFile(file);
        //生成下载路径
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .toUriString();
        fileDownloadUri += fileName;
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    /**
     * 上传多个文件
     *
     * @param files
     * @return
     */
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    /**
     * 下载
     *
     * @param fileName
     * @param request
     * @return
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        // 加载文件
        Resource resource = fileService.loadFileAsResource(fileName);
        // 确定上下文类型
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // 返回默认类型如果无法确定
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(resource.getFilename(), "utf-8") + "\"")
                .body(resource);
    }
}
