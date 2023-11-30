package com.mobiquity.concurrency;

import com.mobiquity.entity.Item;
import com.mobiquity.entity.Package;
import com.mobiquity.knapsack.KnapSack;
import com.mobiquity.knapsack.KnapSackQueueImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapSackQueueImplConcurrencyTest {

    @Test
    public void testConcurrentRequests() throws InterruptedException, ExecutionException {
        int numThreads = 4;
        int numPackages = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Callable<String>> tasks = new ArrayList<>();

        KnapSack knapSack = new KnapSackQueueImpl();

        // Create packages with random items
        List<Package> packages = createPackages(numPackages);

        // Initialize the KnapSack with the packages
        knapSack.init(packages);

        // Create concurrent tasks to collect selected items
        for (int i = 0; i < numThreads; i++) {
            tasks.add(() -> {
                knapSack.collectSelectedItems();
                return knapSack.toString();
            });
        }

        // Execute the tasks concurrently
        List<Future<String>> results = executorService.invokeAll(tasks);

        // Verify the expected output for each task
        assertEquals("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9", results.get(0).get());
        assertEquals("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9", results.get(1).get());
        assertEquals("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9", results.get(2).get());
        assertEquals("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9", results.get(3).get());

        executorService.shutdown();
    }

    private List<Package> createPackages(int numPackages) {
        List<Package> packages = new ArrayList<>();

        // Create packages with the provided items
        packages.add(createPackage(81, "(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)"));
        packages.add(createPackage(8, "(1,15.3,€34)"));
        packages.add(createPackage(75, "(1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)"));
        packages.add(createPackage(56, "(1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)"));

        return packages.subList(0, numPackages);
    }

    private Package createPackage(int weightLimit, String itemsString) {
        String[] itemsArray = itemsString.split(" ");
        List<Item> items = new ArrayList<>();

        for (String itemString : itemsArray) {
            String[] itemData = itemString.substring(1, itemString.length() - 1).split(",");
            int index = Integer.parseInt(itemData[0]);
            double weight = Double.parseDouble(itemData[1]);
            double price = Double.parseDouble(itemData[2].substring(1));

            Item item = new Item(index, weight, price);
            items.add(item);
        }

        return new Package(weightLimit, items);
    }
}