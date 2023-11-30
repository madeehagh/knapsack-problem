package com.mobiquity.service;


import com.mobiquity.constants.MessageConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.knapsack.KnapSackQueueImpl;
import com.mobiquity.entity.Package;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WeightCalculationService {

    // Private constructor to prevent instantiation
    private WeightCalculationService(){}

    private static final Logger logger = LogManager.getLogger(WeightCalculationService.class);

    public static String selectItemsWithWeightConstraint(List<Package> packages) throws APIException {
        validatePackages(packages);
        KnapSackQueueImpl knapSackQueueImpl = new KnapSackQueueImpl();
        knapSackQueueImpl.init(packages);
        knapSackQueueImpl.collectSelectedItems();
        return knapSackQueueImpl.toString();
    }

    private static void validatePackages(List<Package> packages) throws APIException {
        if (packages == null || packages.isEmpty()) {
            logger.error(MessageConstants.EMPTY_PACKAGE_MESSAGE);
            throw new APIException(MessageConstants.EMPTY_PACKAGE_MESSAGE);
        }
    }
}
