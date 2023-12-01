package com.mobiquity.integration.processing;

import com.mobiquity.input.TestInput;
import com.mobiquity.knapsack.KnapSackQueueImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

 class KnapSackQueueImplTest {

    private KnapSackQueueImpl knapSackQueueImpl;

    @BeforeEach
     void setUp() {
        knapSackQueueImpl = new KnapSackQueueImpl();
    }

    @Test
    @DisplayName("Valid input returns expected index of given data")
     void testCalculate() {
        knapSackQueueImpl.init(TestInput.packagesForWeightCalculation());
        knapSackQueueImpl.collectSelectedItems();
        String result = knapSackQueueImpl.toString();
        Assertions.assertEquals("4", result);
    }

    @Test
    @DisplayName("Valid input returns expected - in output")
     void testExceedingWeightCalculate() {
        knapSackQueueImpl.init(TestInput.givenPackageWithExceedingWeight());
        knapSackQueueImpl.collectSelectedItems();
        String result = knapSackQueueImpl.toString();
        Assertions.assertEquals("-", result);
    }

}
