package org.example;

import java.util.*;
import java.util.concurrent.*;

public class CachePerformanceTest {
    private static final int THREAD_COUNT = 16;
    private static final int OPERATIONS_PER_THREAD = 100_000;
    private static final int KEY_RANGE = 1000;

    public static void main(String[] args) throws InterruptedException {
        // Test implementations
        Map<String, String>[] maps = new Map[]{
                Collections.synchronizedMap(new HashMap<>()),
                new ConcurrentHashMap<>(),
                Collections.synchronizedSortedMap(new TreeMap<>()),
                new ConcurrentSkipListMap<>()
        };

        String[] names = {
                "Synchronized HashMap",
                "ConcurrentHashMap",
                "Synchronized TreeMap",
                "ConcurrentSkipListMap"
        };

        // Run tests
        long[] times = new long[maps.length];
        for (int i = 0; i < maps.length; i++) {
            gCache<String> cache = new gCache<>(maps[i]);
            times[i] = testPerformance(cache, names[i]);
            Thread.sleep(1000); // Cool down
        }

        // Print summary
        System.out.println("\nPerformance Summary (ms):");
        for (int i = 0; i < maps.length; i++) {
            System.out.printf("%-25s %d%n", names[i] + ":", times[i]);
        }
    }

    private static long testPerformance(gCache<String> cache, String name)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // Warm up
        Thread.sleep(100);
        System.gc();

        // Prefill with some data
        for (int i = 0; i < KEY_RANGE; i++) {
            cache.add(String.format("key-%03d", i), "value-" + i);
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        // Simulate real-world access patterns
                        int op = random.nextInt(100);
                        String key = String.format("key-%03d", random.nextInt(KEY_RANGE));

                        if (op < 80) {  // 80% reads
                            cache.get(key);
                        } else if (op < 95) {  // 15% writes
                            cache.add(key, "value-" + j);
                        } else {  // 5% range-based operations
                            // Simulate range operations by accessing sequential keys
                            int start = random.nextInt(KEY_RANGE - 10);
                            for (int k = 0; k < 10; k++) {
                                cache.get(String.format("key-%03d", start + k));
                            }
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println(name + " took: " + duration + " ms");
        return duration;
    }
}