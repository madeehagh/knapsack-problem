/*
package com.mobiquity.integration.processing;

import com.mobiquity.input.TestInput;
import com.mobiquity.processing.KnapSackQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentRequest {

    private KnapSackQueue knapSackQueue = KnapSackQueue.getInstance();

    @Test
    @DisplayName("should execute knapsack init method for the given number of concurrent requests")
    public void concurrentRequestsCount() throws InterruptedException {
        int numOfThreads = 8;
        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(numOfThreads);
            CountDownLatch countDownLatch = new CountDownLatch(numOfThreads);
            List<String> responses = new ArrayList<>();

            for (int i = 0; i< numOfThreads; i++) {
                executor.submit(() -> {
                    String response = knapSackQueue.init(TestInput.packagesForWightCal())
                            .getSelectedItems().toString();
                    responses.add(response);
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            Assertions.assertEquals(numOfThreads, responses.size());
        } finally {
            shutdownExecutor(executor);
        }
    }

    @Test
    @DisplayName("concurrent requests should not modify each other's responses")
    public void concurrentRequestShouldNotModifyResponses() throws InterruptedException {
        int numOfThreads = 8;
        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(numOfThreads);
            CountDownLatch countDownLatch = new CountDownLatch(numOfThreads);
            List<KnapSackQueue> responses = new ArrayList<>();

            for (int i = 0; i< numOfThreads; i++) {
                executor.submit(() -> {
                     KnapSackQueue knapSackQueueObj = concurrentPackageParsing();
                    responses.add(knapSackQueueObj.);
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            System.out.println(responses.get(1));
            System.out.println(responses.get(2));
            Assertions.assertFalse(Objects.equals(responses.get(1), responses.get(1)));
        } finally {
            shutdownExecutor(executor);
        }
    }

    private KnapSackQueue concurrentPackageParsing() {
        knapSackQueue.init(TestInput.packagesForWightCal())
                .getSelectedItems();
        System.out.println(knapSackQueue);
        return knapSackQueue;
    }

    private void shutdownExecutor(ExecutorService executor) throws InterruptedException {
        if(executor == null) {
            return;
        }
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
*/
