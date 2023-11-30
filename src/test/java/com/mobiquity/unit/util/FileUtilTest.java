package com.mobiquity.unit.util;

import com.mobiquity.constants.MessageConstants;
import com.mobiquity.util.FileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("should return expected List, if valid path is given")
    public void testReadFileWhenValidFilePathGiven() throws Exception {
        // Arrange
        File tempFile = tempDir.resolve("tempFile.txt").toFile();
        try (PrintWriter out = new PrintWriter(tempFile)) {
            out.println("item1");
            out.println("item2");
            out.println("item3");
        }

        // Act
        List<String> result = FileUtil.readFile(tempFile.getAbsolutePath());

        // Assert
        assertEquals(3, result.size());
        assertEquals("item1", result.get(0));
        assertEquals("item2", result.get(1));
        assertEquals("item3", result.get(2));
    }

    @Test
    @DisplayName("should throw exception if file is not found")
    public void testReadFileWhenNullFilePathGiven() {
        // Act and Assert
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> FileUtil.readFile(null));
        assertEquals(MessageConstants.INVALID_FILE_PATH, exception.getMessage());
    }

    @Test
    @DisplayName("should throw exception if file path is invalid")
    public void testThrowExceptionForInvalidPath() {
        // Act and Assert
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> FileUtil.readFile("nonExistentFile.txt"));
        assertEquals(MessageConstants.FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("should return empty list, if file doesn't contain any item")
    public void testReadEmptyFile() throws Exception {
        // Arrange
        File tempFile = tempDir.resolve("emptyFile.txt").toFile();
        try (PrintWriter out = new PrintWriter(tempFile)) {
            // Write nothing to file
        }

        // Act
        List<String> result = FileUtil.readFile(tempFile.getAbsolutePath());

        // Assert
        assertEquals(0, result.size());
    }
}