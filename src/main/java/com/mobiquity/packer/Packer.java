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

  public static String pack(String filePath) throws APIException, FileNotFoundException {

    List<Package> packages = FileParserService.parseFile(
            FileReaderService.readFile(filePath));

    return WeightCalculationService.selectItemsWithWeightConstraint(packages);
  }
}
