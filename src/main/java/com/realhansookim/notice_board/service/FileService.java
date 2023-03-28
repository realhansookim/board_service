package com.realhansookim.notice_board.service;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
public String saveImageFile(String type, MultipartFile file) throws Exception{
    Path targetLocation = Paths.get("/music/image/"+type);
    String fileName = file.getOriginalFilename();
    String [] split = fileName.split("\\.");
    String ext = split[split.length-1];
    fileName = generateRandomStr()+"."+ext;
    targetLocation = targetLocation.resolve(fileName);
    Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
    return fileName;
}

public ResponseEntity<Resource> getImageFile(String type, String fileName) throws Exception {
    Path fileLocation = Paths.get("/music/image/"+type);
    Resource r = new UrlResource(fileLocation.toUri());
    String contentType = "application/octet-stream";
    
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, 
        "attachment; filename=\""+URLEncoder.encode(r.getFilename(), "UTF-8"))
        .body(r);
    }

public static String generateRandomStr(){
    int leftLimit = 97;
    int rightLimit = 122;
    int targetStringLength = (int)Math.floor(Math.random()*5)+6;
    Random random = new Random();
    String generatedString = random.ints(leftLimit, rightLimit + 1)
    .limit(targetStringLength)
    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    .toString();
    Date dt = new Date();
    generatedString += dt.getTime();
    return generatedString;

    
}
}
