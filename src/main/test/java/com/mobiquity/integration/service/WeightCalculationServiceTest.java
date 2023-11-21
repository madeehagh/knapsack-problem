package com.mobiquity.integration.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.WeightCalculationService;
import com.mobiquity.input.TestInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeightCalculationServiceTest {

    @Test
    @DisplayName("Should return expected result when valid input is given")
    public void calculateWeightForGivenPackages() throws APIException {
        String result = WeightCalculationService
                .selectItemsWithWeightConstraint(
                        TestInput.packagesForWightCal());
        Assertions.assertEquals("4", result);
    }

    @Test
    @DisplayName("Should throw APIException when input is null")
    public void calculateWtForNullGivenPackages() {
            Assertions.assertThrows(APIException.class, () -> WeightCalculationService.selectItemsWithWeightConstraint(null));
    }
}
