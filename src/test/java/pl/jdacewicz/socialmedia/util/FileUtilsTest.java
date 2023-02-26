package pl.jdacewicz.socialmedia.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void ProperGeneratedFileNameExtensionWhenFileWithExtensionProvided() {
        String fileName = "originalFileName.png";
        String generatedFileName = FileUtils.generateUniqueName(fileName);

        String extension = generatedFileName.substring(generatedFileName.length() - 4, generatedFileName.length());

        assertEquals(".png", extension);
    }

    @Test
    void ProperGeneratedFileNameExtensionWhenFileWithoutExtensionProvided() {
        String fileName = "originalFileName";
        String generatedFileName = FileUtils.generateUniqueName(fileName);

        boolean extensionNotIncluded = (generatedFileName.lastIndexOf('.') == -1);

        assertTrue(extensionNotIncluded);
    }

    //GeneratedFileNameLengthEquals8
    @Test
    void ProperGeneratedFileNameLengthWhenFileWithExtensionProvided() {
        String fileName = "originalFileName.png";
        String generatedFileName = FileUtils.generateUniqueName(fileName);

        String nameWithoutExtension = generatedFileName.substring(0, generatedFileName.length() - 4);

        assertEquals(8, nameWithoutExtension.length());
    }

    @Test
    void ProperGeneratedFileNameLengthWhenFileWithoutExtensionProvided() {
        String fileName = "originalFileName";
        String generatedFileName = FileUtils.generateUniqueName(fileName);

        assertEquals(8, generatedFileName.length());
    }
}