package com.micro.user.service.controller;

import com.micro.user.service.entities.FileDTO;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {


//        @Autowired
//        private FileService fileService;

        @PostMapping("/uploadFilesIntoDB")
        public ResponseEntity<String> storeFilesIntoDB(@RequestParam MultipartFile file) throws IOException {
//            fileService.save(file);

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }
    }



