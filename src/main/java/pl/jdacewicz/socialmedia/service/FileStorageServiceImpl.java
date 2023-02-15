package pl.jdacewicz.socialmedia.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final static Path UPLOAD_DIRECTORY = Paths.get("uploads");

    public void save(MultipartFile file, String fileName){
        try {
            Path filePath = UPLOAD_DIRECTORY.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource load(String fileName) {
        try {
            Path file = UPLOAD_DIRECTORY.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
