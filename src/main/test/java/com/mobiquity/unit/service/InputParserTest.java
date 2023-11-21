package com.mobiquity.unit.service;

import com.mobiquity.entity.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.util.InputParser;
import com.mobiquity.input.TestInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InputParserTest {


    @Test
    @DisplayName("Parse file method should be called exactly once")
    public void testParseFile() throws APIException {
        List<Package> packageList = InputParser.parseFile(TestInput.fileReaderInput());
        Assertions.assertTrue(packageList.size() > 0);
    }

    @Test
    @DisplayName("APIException should be thrown when null/empty file data given ")
    public void testMissingWtDelimiter() {
        Assertions.assertThrows(APIException.class, () ->
                InputParser.parseFile(null));
    }
}
