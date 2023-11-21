package com.mobiquity.unit.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.WeightCalculationService;
import com.mobiquity.input.TestInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeightCalculationServiceTest {

    @Test
    @DisplayName("Calculate weight method should be called exactly once")
    public void testCalculateWeight() throws APIException {
        String result = WeightCalculationService.selectItemsWithWeightConstraint(TestInput.packagesForWightCal());
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("APIException should be thrown when null/empty packages are given ")
    public void testMissingWtDelimiter() {
        Assertions.assertThrows(APIException.class, () ->
                WeightCalculationService.selectItemsWithWeightConstraint(null));
    }
}
