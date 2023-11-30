package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.util.InputParser;
import com.mobiquity.util.FileUtil;
import com.mobiquity.service.WeightCalculationService;
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
   * @throws APIException  if there is an API-related exception
   * @throws FileNotFoundException if the file is not found
   */
  public static String pack(String filePath) throws APIException, FileNotFoundException {
    try {
      List<Package> packages = InputParser.parseFile(
              FileUtil.readFile(filePath));
      return WeightCalculationService.selectItemsWithWeightConstraint(packages);
    } catch (FileNotFoundException fe) {
      throw new FileNotFoundException(fe.getMessage());
    } catch (APIException apiException) {
      throw new APIException(apiException.getMessage());
    }
  }
}
