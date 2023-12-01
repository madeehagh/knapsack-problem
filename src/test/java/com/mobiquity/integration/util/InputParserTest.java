package com.mobiquity.integration.util;

import com.mobiquity.entity.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.input.TestInput;
import com.mobiquity.util.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mobiquity.input.TestInput.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {


    @Test
    @DisplayName("Valid input file data should return expected non-empty output")
    void testValidInputShouldGiveExpectedOutput() throws APIException {
        List<Package> packages = InputParser.parseFile(givenPackages());
        Assertions.assertTrue(packages.size() > 0);
    }

    @Test
    @DisplayName("Empty file path should return APIException")
    void testNullFilePath() {
        assertThrows(APIException.class, () -> {
            InputParser.parseFile(null);
        });
    }

    @Test
    @DisplayName("Missing delimiter should throw FileParser Exception")
    void testMissingWtDelimiter() {
        assertThrows(FileParserException.class,
                () -> {
                    InputParser.parseFile(
                            givenPackageWithInvalidWeightDelimiter());
                });
    }

    @Test
    @DisplayName("Invalid Index in input package should throw FileParser Exception")
    void testInvalidIndexInInputPkgs() {
        assertThrows(FileParserException.class, () -> InputParser.parseFile(givenPackageWithInvalidIndex()));
    }

    @Test
    @DisplayName("Invalid Weight in input package should throw FileParser Exception")
    void testInvalidWeightInInputPkgs() {
        assertThrows(FileParserException.class, () -> InputParser.parseFile(givenPackageWithInvalidWeight()));
    }

    @Test
    @DisplayName("Invalid Price in input package should throw FileParser Exception")
    void testInvalidPriceInInputPkgs() {
        assertThrows(FileParserException.class, () -> InputParser.parseFile(givenPackageWithInvalidPrice()));
    }

    @Test
    @DisplayName("Invalid Cost in input package should throw FileParser Exception")
    void testInvalidCostInInputPkgs() {
        assertThrows(FileParserException.class, () -> InputParser.parseFile(givenPackageWithInvalidPrice()));
    }

    @Test
    @DisplayName("Invalid attributes in input package should throw API Exception")
    void testInvalidAttributesInInputPkgs() {
        assertThrows(APIException.class, () -> InputParser.parseFile(givenPackageWithInvalidItemAttr()));
    }

}
