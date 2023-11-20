package com.mobiquity.unit.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.input.TestConstants;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class PackerTest {

    @Test
    @DisplayName("The pack method should be called exactly once")
    public void testPack() throws APIException, FileNotFoundException {
        String result = Packer.pack(TestConstants.INPUT_FILE_PATH);
        Assertions.assertTrue(result != null && !result.isEmpty());
    }

    @Test
    @DisplayName("The pack method should throw FileNotFoundException for Invalid input")
    public void testException()  {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                Packer.pack(TestConstants.INVALID_INPUT_FILE_PATH));
    }
}
