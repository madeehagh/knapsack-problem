package com.mobiquity.service;


import com.mobiquity.exception.APIException;
import com.mobiquity.knapsack.KnapSackQueueImpl;
import com.mobiquity.entity.Package;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WeightCalculationService {

    private static final Logger logger = LogManager.getLogger(WeightCalculationService.class);

    public static String selectItemsWithWeightConstraint(List<Package> packages) throws APIException {
        KnapSackQueueImpl knapSackQueueImpl = new KnapSackQueueImpl();
        if (null == packages || packages.isEmpty()) {
            logger.error("Packages does not have any item(s)");
           throw new APIException("Packages does not have any item(s)");
        }
       knapSackQueueImpl.init(packages);
       knapSackQueueImpl.collectSelectedItems();
       return knapSackQueueImpl.toString();
    }
}
