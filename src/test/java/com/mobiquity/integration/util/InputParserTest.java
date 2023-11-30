package com.mobiquity.integration.util;

import com.mobiquity.entity.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.util.InputParser;
import input.TestInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InputParserTest {


    @Test
    @DisplayName("Valid input file data should return expected non-empty output")
    public void testValidInputShouldGiveExpectedOutput() throws APIException {
        List<Package> packages = InputParser.parseFile(TestInput.givenPackages());
        Assertions.assertTrue(packages.size() > 0);
    }

    @Test
    @DisplayName("Empty file path should return APIException")
    public void testNullFilePath() {
        Assertions.assertThrows(APIException.class, () -> InputParser.parseFile(null));
    }

    @Test
    @DisplayName("Missing delimiter should throw FileParser Exception")
    public void testMissingWtDelimiter() {
        Assertions.assertThrows(FileParserException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidWeightDelimiter()));
    }

    @Test
    @DisplayName("Invalid Index in input package should throw FileParser Exception")
    public void testInvalidIndexInInputPkgs() {
        Assertions.assertThrows(FileParserException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidIndex()));
    }

    @Test
    @DisplayName("Invalid Weight in input package should throw FileParser Exception")
     void testInvalidWeightInInputPkgs() {
        Assertions.assertThrows(FileParserException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidWeight()));
    }

    @Test
    @DisplayName("Invalid Price in input package should throw FileParser Exception")
     void testInvalidPriceInInputPkgs() {
        Assertions.assertThrows(FileParserException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidPrice()));
    }

    @Test
    @DisplayName("Invalid Cost in input package should throw FileParser Exception")
     void testInvalidCostInInputPkgs() {
        Assertions.assertThrows(FileParserException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidPrice()));
    }

    @Test
    @DisplayName("Invalid attributes in input package should throw API Exception")
     void testInvalidAttributesInInputPkgs() {
        Assertions.assertThrows(APIException.class, () ->
                InputParser.parseFile(TestInput.givenPackageWithInvalidItemAttr()));
    }

}
