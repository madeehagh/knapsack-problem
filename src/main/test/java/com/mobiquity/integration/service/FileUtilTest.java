package com.mobiquity.integration.service;

import com.mobiquity.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.mobiquity.input.TestConstants.*;

public class FileUtilTest {
    
    @Test
    @DisplayName("Should return expected value when input packages are correct")
    public void testCorrectOutput() throws FileNotFoundException {
        List<String> result = FileUtil.readFile(INPUT_FILE_PATH);
        Assertions.assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Should return FileNotFoundException when invalid file path given")
    public void testThrowExceptionForIncorrectPath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> FileUtil.readFile(INVALID_INPUT_FILE_PATH));
    }

    @Test
    @DisplayName("Should return APIException when invalid file path is empty")
    public void testThrowExceptionForEmptyPath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> FileUtil.readFile(""));
    }

    @Test
    @DisplayName("Should return empty string when file is empty")
    public void testReturnEmptyWhenFileIsEmpty() throws FileNotFoundException {
        Assertions.assertEquals(new ArrayList<>(), FileUtil.readFile(INPUT_EMPTY_FILE_PATH));
    }
}
