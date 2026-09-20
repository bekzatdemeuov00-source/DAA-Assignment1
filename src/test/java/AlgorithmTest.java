import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    @Test
    void testMergeSort() {
        Random random = new Random();
        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1000);
            int[] actual = new int[size];
            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt(10000);
            }
            int[] expected = actual.clone();
            Arrays.sort(expected);

            MergeSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }
    @Test
    void testQuickSort() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1000);
            int[] actual = new int[size];
            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt(10000);
            }
            int[] expected = actual.clone();
            Arrays.sort(expected);

            QuickSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }
    @Test
    void testEdgeCases() {
        int[][] arrays = {
                {},
                {5},
                {7, 7, 7, 7, 7},
                {1, 2, 3, 4, 5}
        };

        for (int[] array : arrays) {
            int[] expected = array.clone();
            Arrays.sort(expected);

            int[] mergeArray = array.clone();
            MergeSort.sort(mergeArray, new Metrics());
            assertArrayEquals(expected, mergeArray);

            int[] quickArray = array.clone();
            QuickSort.sort(quickArray, new Metrics());
            assertArrayEquals(expected, quickArray);
        }
    }

    @Test
    void testQuickSortDepth() {
        int n = 100000;
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(array, metrics);

        double limit = 2 * (Math.log(n) / Math.log(2));

        assertTrue(metrics.maxDepth <= limit);
    }

    @Test
    void testQuickSelect() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(999) + 1;
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(10000);
            }

            int[] sorted = array.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(size);

            int result = QuickSelect.select(
                    array.clone(),
                    k,
                    new Metrics()
            );

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testQuickSelectInvalidInput() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{}, 0, new Metrics())
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 5, new Metrics())
        );
    }
}