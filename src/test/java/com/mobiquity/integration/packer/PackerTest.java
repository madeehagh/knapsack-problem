package com.mobiquity.integration.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ProcessingException;
import com.mobiquity.input.TestConstants;
import com.mobiquity.input.TestInput;
import com.mobiquity.packer.Packer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mobiquity.input.TestConstants.INVALID_INPUT_FILE_PATH;


class PackerTest {

    @Test
    @DisplayName("Valid file path and content should return expected output")
     void testValidFile() throws APIException, IOException, ProcessingException {
        Assertions.assertEquals(TestInput.expectedInputFileResult(),
                Packer.pack(TestConstants.INPUT_FILE_PATH));
    }

    @Test
    @DisplayName("Invalid file path should return FileNotFoundException")
     void testInvalidFilePath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> Packer.pack(INVALID_INPUT_FILE_PATH));
    }

    @Test
    @DisplayName("Empty file path should return FPE")
     void testEmptyFilePath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> Packer.pack(""));
    }

    @Test
    @DisplayName("Empty file path should return FileNOtFoundException")
     void testNullFilePath() {
        Assertions.assertThrows(FileNotFoundException.class, () -> Packer.pack(null));
    }
}
