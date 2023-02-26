package pl.jdacewicz.socialmedia.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    String fileName = "originalFileName.png";
    String generatedFileName = FileUtils.generateUniqueName(fileName);

    @Test
    void GeneratedFileNameExtensionSameAsOriginalFile() {
        int dotIndex = generatedFileName.lastIndexOf('.');
        String extension = generatedFileName.substring(dotIndex, generatedFileName.length());

        assertEquals(".png", extension);
    }

    @Test
    void GeneratedFileNameLengthWithoutExtensionEquals8() {
        int dotIndex = generatedFileName.lastIndexOf('.');
        String nameWithoutExtension = generatedFileName.substring(0, dotIndex);

        assertEquals(8, nameWithoutExtension.length());
    }
}