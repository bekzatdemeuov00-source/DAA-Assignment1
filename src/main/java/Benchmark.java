import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final Random random = new Random();

    private static int[] createArray(int n, String type) {
        int[] a = new int[n];

        if (type.equals("random")) {
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt();
            }
        }

        else if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
        }

        else if (type.equals("duplicates")) {
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(10);
            }
        }

        return a;
    }

    private static int medianIndex(long[] times) {
        long[] sorted = times.clone();
        Arrays.sort(sorted);

        long median = sorted[sorted.length / 2];

        for (int i = 0; i < times.length; i++) {
            if (times[i] == median) {
                return i;
            }
        }

        return 0;
    }

    private static void runAlgorithm(
            String algorithm,
            String inputType,
            int n,
            int[] original,
            PrintWriter writer
    ) {

        long[] times = new long[5];
        long[] comparisons = new long[5];
        int[] depths = new int[5];

        for (int run = 0; run < 5; run++) {

            int[] a = original.clone();
            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            if (algorithm.equals("MergeSort")) {
                MergeSort.sort(a, metrics);
            }

            else if (algorithm.equals("QuickSort")) {
                QuickSort.sort(a, metrics);
            }

            else if (algorithm.equals("QuickSelect")) {
                int k = a.length / 2;
                QuickSelect.select(a, k, metrics);
            }

            long end = System.nanoTime();

            times[run] = end - start;
            comparisons[run] = metrics.comparisons;
            depths[run] = metrics.maxDepth;
        }

        int medianIndex = medianIndex(times);

        double timeMs = times[medianIndex] / 1_000_000.0;

        writer.println(
                algorithm + "," +
                        inputType + "," +
                        n + "," +
                        timeMs + "," +
                        comparisons[medianIndex] + "," +
                        depths[medianIndex]
        );

        System.out.println(
                algorithm +
                        ": " + timeMs +
                        " ms, comparisons = " +
                        comparisons[medianIndex] +
                        ", depth = " +
                        depths[medianIndex]
        );
    }

    public static void main(String[] args) throws Exception {

        int[] sizes = {
                1_000,
                10_000,
                100_000,
                1_000_000
        };

        String[] types = {
                "random",
                "sorted",
                "duplicates"
        };

        PrintWriter writer = new PrintWriter("results.csv");

        writer.println(
                "algorithm,input,n,time_ms,comparisons,max_depth"
        );

        for (int n : sizes) {

            for (String type : types) {

                System.out.println();
                System.out.println(
                        "n = " + n +
                                ", input = " + type
                );

                int[] original = createArray(n, type);

                runAlgorithm(
                        "MergeSort",
                        type,
                        n,
                        original,
                        writer
                );

                runAlgorithm(
                        "QuickSort",
                        type,
                        n,
                        original,
                        writer
                );

                runAlgorithm(
                        "QuickSelect",
                        type,
                        n,
                        original,
                        writer
                );
            }
        }

        writer.close();

        System.out.println();
        System.out.println("Benchmark finished.");
        System.out.println("Results saved to results.csv");
    }
}