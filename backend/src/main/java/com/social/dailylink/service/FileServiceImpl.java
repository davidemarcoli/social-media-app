package com.social.dailylink.service;

import com.social.dailylink.model.File;
import com.social.dailylink.repository.FileRepository;
import com.social.dailylink.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${upload-dir}")
    private String uploadDir;

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File saveFile(MultipartFile file) throws IOException {
        UUID id = UUID.randomUUID();
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        String extension = FileUtils.getExtension(fileName);
        String fileNameWithoutExtension = FileUtils.getFileNameWithoutExtension(fileName);
        byte[] content = FileUtils.compressImage(file.getBytes());
        String newFileName = fileNameWithoutExtension + "_" + id + "." + extension;
        String filePath = uploadDir + newFileName;
        java.io.File uploadDirFile = new java.io.File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(new String(content));
        fileWriter.close();
        return fileRepository.save(new File(id, fileName, fileType, filePath));
    }

    @Override
    public java.io.File getFileById(UUID id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
        java.io.File newFile = new java.io.File(file.getPath());
        byte[] content = new byte[(int) newFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            fileInputStream.read(content);
            fileInputStream.close();
        } catch (Exception ignored) {
        }
        byte[] decompressContent = FileUtils.decompressImage(content);
        try {
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(new String(decompressContent));
            fileWriter.close();
        } catch (Exception ignored) {
        }
        return newFile;
    }

    @Override
    public void deleteFileById(UUID id) {

    }
}
