package com.mobiquity.concurrency;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ProcessingException;
import com.mobiquity.input.TestConstants;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackerConcurrencyTest {

    @Test
    public void testConcurrentPacking() throws InterruptedException, ExecutionException {
        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Callable<String>> tasks = new ArrayList<>();

        String filePath = TestConstants.INPUT_FILE_PATH;

        // Create concurrent tasks to invoke the pack method
        for (int i = 0; i < numThreads; i++) {
            tasks.add(() -> {
                try {
                    return Packer.pack(filePath);
                } catch (APIException | FileNotFoundException | ProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Execute the tasks concurrently
        List<Future<String>> results = executorService.invokeAll(tasks);

        // Verify that all tasks completed successfully and produced the same result
        String expectedOutput = results.get(0).get();
        for (Future<String> result : results) {
            assertEquals(expectedOutput, result.get());
        }

        executorService.shutdown();
    }
}