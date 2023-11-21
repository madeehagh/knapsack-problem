package com.mobiquity.integration.processing;

import com.mobiquity.input.TestInput;
import com.mobiquity.knapsack.KnapSackQueueImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnapSackQueueImplTest {

    private KnapSackQueueImpl knapSackQueueImpl;

    @BeforeEach
    public void setUp() {
        knapSackQueueImpl = new KnapSackQueueImpl();
    }

    @Test
    @DisplayName("Valid input returns expected index of given data")
    public void testCalculate() {
        knapSackQueueImpl.init(TestInput.packagesForWightCal());
        knapSackQueueImpl.collectSelectedItems();
        String result = knapSackQueueImpl.toString();
        Assert.assertEquals("4", result);
    }

    @Test
    @DisplayName("Valid input returns expected - in output")
    public void testExceedingWeightCalculate() {
        knapSackQueueImpl.init(TestInput.pkgWithWtItemsMoreThanCapacity());
        knapSackQueueImpl.collectSelectedItems();
        String result = knapSackQueueImpl.toString();
        Assert.assertEquals("-", result);
    }

}
