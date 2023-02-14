package pl.jdacewicz.socialmedia.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public static final String UPLOAD_DIRECTORY = "/uploads";

    public Path uploadFile(MultipartFile file) throws IOException{
        StringBuilder fileName = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());

        fileName.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        return fileNameAndPath;
    }
}
