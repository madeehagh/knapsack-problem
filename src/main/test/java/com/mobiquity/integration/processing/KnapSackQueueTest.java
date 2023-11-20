package com.mobiquity.integration.processing;

import com.mobiquity.input.TestInput;
import com.mobiquity.processing.KnapSackQueue;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnapSackQueueTest {

    private KnapSackQueue knapSackQueue = KnapSackQueue.getInstance();

    @Test
    @DisplayName("Valid input returns expected index of given data")
    public void testCalculate() {
        String result = knapSackQueue.init(TestInput.packagesForWightCal()).getSelectedItems().toString();
        Assert.assertEquals("4", result);
    }

    @Test
    @DisplayName("Valid input returns expected - in output")
    public void testExceedingWeightCalculate() {
        String result = knapSackQueue.init(TestInput.pkgWithWtItemsMoreThanCapacity())
                .getSelectedItems()
                .toString();
        Assert.assertEquals("-", result);
    }

    @BeforeEach
    public void clean() {
        knapSackQueue.clear();
    }
}
