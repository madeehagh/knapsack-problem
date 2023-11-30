package com.mobiquity.integration.util;

import com.mobiquity.exception.ProcessingException;
import com.mobiquity.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static input.TestConstants.*;

class FileUtilTest {

    @Test
    @DisplayName("Should return expected value when input packages are correct")
    void testCorrectOutput() throws FileNotFoundException, ProcessingException {
        List<String> result = FileUtil.readFile(INPUT_FILE_PATH);
        Assertions.assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Should return FileNotFoundException when invalid file path given")
     void testThrowExceptionForIncorrectPath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> FileUtil.readFile(INVALID_INPUT_FILE_PATH));
    }

    @Test
    @DisplayName("Should return APIException when invalid file path is empty")
     void testThrowExceptionForEmptyPath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> FileUtil.readFile(""));
    }

    @Test
    @DisplayName("Should return empty string when file is empty")
     void testReturnEmptyWhenFileIsEmpty() throws FileNotFoundException, ProcessingException {
        Assertions.assertEquals(new ArrayList<>(), FileUtil.readFile(INPUT_EMPTY_FILE_PATH));
    }
}
