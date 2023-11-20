package com.mobiquity.handles;


import com.mobiquity.exception.APIException;
import com.mobiquity.processing.KnapSackQueue;
import com.mobiquity.entity.Package;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WeightCalculationService {

    private static final Logger logger = LogManager.getLogger(WeightCalculationService.class);
    private static final KnapSackQueue knapSackQueue = KnapSackQueue.getInstance();

    public static String selectItemsWithWeightConstraint(List<Package> packages) throws APIException {
        knapSackQueue.clear();
        if (null == packages || packages.isEmpty()) {
            logger.error("Packages does not have any item(s)");
           throw new APIException("Packages does not have any item(s)");
        }

       knapSackQueue.init(packages);
       knapSackQueue.getSelectedItems();
       return knapSackQueue.toString();
    }
}
