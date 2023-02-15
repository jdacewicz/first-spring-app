package pl.jdacewicz.socialmedia.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void save(MultipartFile file, String fileName);
    Resource load(String fileName);
}
