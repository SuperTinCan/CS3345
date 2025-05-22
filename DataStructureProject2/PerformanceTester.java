public class PerformanceTester {
    public static <T> void test(String label, DataStructure<T> ds, T[] insertData, T[] searchData, T[] deleteData) {
        long memoryBefore, startTime, endTime;
        Runtime runtime = Runtime.getRuntime();

        // Insertion
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        startTime = System.nanoTime();
        for (T key : insertData) ds.insert(key);
        endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("%s - Insert: %d ms, Memory: %d KB%n", label, (endTime - startTime) / 1_000_000, (memoryAfter - memoryBefore) / 1024);

        // Search
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        startTime = System.nanoTime();
        for (T key : searchData) ds.contains(key);
        endTime = System.nanoTime();
        System.out.printf("%s - Search: %d ms, Memory: %d KB%n", label, (endTime - startTime) / 1_000_000, (memoryAfter - memoryBefore) / 1024);

        // Deletion
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        startTime = System.nanoTime();
        for (T key : deleteData) ds.delete(key);
        endTime = System.nanoTime();
        System.out.printf("%s - Delete: %d ms, Memory: %d KB%n", label, (endTime - startTime) / 1_000_000, (memoryAfter - memoryBefore) / 1024);
    }
}
