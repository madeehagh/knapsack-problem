package com.mobiquity.unit.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.input.TestInput;
import com.mobiquity.service.WeightCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

 class WeightCalculationServiceTest {

    @Test
    @DisplayName("Calculate weight method should be called exactly once")
     void testCalculateWeight() throws APIException {
        String result = WeightCalculationService
                .selectItemsWithWeightConstraint(TestInput.packagesForWeightCalculation());
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("APIException should be thrown when null/empty packages are given ")
     void testMissingWtDelimiter() {
        Assertions.assertThrows(APIException.class, () ->
                WeightCalculationService.selectItemsWithWeightConstraint(null));
    }
}
