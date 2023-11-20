package com.mobiquity.handles;


import com.mobiquity.entity.Item;
import com.mobiquity.entity.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.helper.PatternConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FileParserService {

    private static final Logger logger = LogManager.getLogger(FileParserService.class);

    /**
     * Takes fileItems from the given File and process it to return list of Package Objects
     * ie: items in format 81 :((2,14.55,€74))
     * @param fileItems
     * @return list of Package object
     * @throws APIException
     */
    public static List<Package> parseFile(List<String> fileItems) throws APIException {
        if (null == fileItems || fileItems.isEmpty()) {
            logger.error("No data present in file");
            throw new APIException("No data present in file");
        }
        List<Package> list = new ArrayList<>();
        for (String fileItem : fileItems) {
            Package packageItem = getPackageItem(fileItem);
            list.add(packageItem);
        }
        return list;
    }

    private static Package getPackageItem(String items) throws APIException {
        try {
            String[] unprocessedItems = items.split(PatternConstants.WEIGHT_SPLIT_DELIMITER);
            double weight = Double.parseDouble(unprocessedItems[0]);
            String[] readyToParseItems = unprocessedItems[1].split(PatternConstants.ITEMS_SPLIT_PATTERN);

            List<Item> itemList = new ArrayList<>();

            for (String readyToParseItem : readyToParseItems) {
                Item item = getItem(readyToParseItem);
                if (item != null) {
                    itemList.add(item);
                }
            }
            return new Package(weight, itemList);
        } catch (APIException ae) {
            logger.error(ae.getMessage());
            throw new APIException(ae.getMessage());
        }
        catch (NumberFormatException | FileParserException fe) {
            logger.error(fe.getMessage());
            throw new FileParserException(fe.getMessage());
        }
    }

    private static Item getItem(String item) throws APIException {
        if (null == item || item.isBlank())
            return null;
        String[] splitItems = item.split(PatternConstants.ITEMS_SPLIT_DELIMITER);

        if (splitItems.length != PatternConstants.MAX_ENTITIES_IN_ITEMS) {
            throw new APIException("Invalid or missing entities in package");
        }

        try {
            return new Item(
                    Integer.parseInt(splitItems[0]),
                    Double.parseDouble(splitItems[1]),
                    Double.parseDouble(splitItems[2].substring(1)));
        } catch (NumberFormatException e) {
            logger.error("Invalid input", e);
            throw new NumberFormatException(e.getMessage());
        }
    }
}
