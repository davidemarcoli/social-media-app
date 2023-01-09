package com.social.dailylink.service;

import com.social.dailylink.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public interface FileService {
    File saveFile(MultipartFile file) throws IOException;
    java.io.File getFileById(UUID id);
    void deleteFileById(UUID id);
}
