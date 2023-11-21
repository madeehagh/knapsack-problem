package com.mobiquity.unit.service;

import com.mobiquity.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class FileUtilTest {

    @Test
    @DisplayName("FileNotFoundException should be thrown when null/empty packages are given ")
    public void testMissingWtDelimiter() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                FileUtil.readFile(null));
    }
}
