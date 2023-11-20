package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.handles.FileParserService;
import com.mobiquity.handles.FileReaderService;
import com.mobiquity.handles.WeightCalculationService;
import com.mobiquity.entity.Package;

import java.io.FileNotFoundException;
import java.util.List;

public class Packer {

  private Packer() {}

  /**
   * This method is the entry point, which takes input as file with data format 75 : (1,85.31,€29) (2,14.55,€74).
   * Calculates and return all items that can be selected not exceeding the given capacity.
   * @param filePath absolute path to file in UTF-8 format
   * @return solution as String
   * @throws APIException
   * @throws FileNotFoundException
   */
  public static String pack(String filePath) throws APIException, FileNotFoundException {

    List<Package> packages = FileParserService.parseFile(
            FileReaderService.readFile(filePath));

    return WeightCalculationService.selectItemsWithWeightConstraint(packages);
  }
}
