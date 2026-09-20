import java.util.concurrent.ThreadLocalRandom;

public class QuickSelect {
    public static int select(int[] a, int k, Metrics metrics) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k is out of range");
        }
        int left = 0;
        int right = a.length - 1;
        int depth = 1;

        while (left <= right) {
            metrics.updateDepth(depth);
            int pivotIndex =
                    ThreadLocalRandom.current().nextInt(left, right + 1);
            int pivot = a[pivotIndex];
            int lt = left;
            int i = left;
            int gt = right;
            while (i <= gt) {
                metrics.compare();
                if (a[i] < pivot) {
                    swap(a, i, lt);
                    i++;
                    lt++;
                } else {
                    metrics.compare();
                    if (a[i] > pivot) {
                        swap(a, i, gt);
                        gt--;
                    } else {
                        i++;
                    }
                }
            }
            if (k < lt) {
                right = lt - 1;
            } else if (k > gt) {
                left = gt + 1;
            } else {
                return a[k];
            }
            depth++;
        }
        throw new IllegalStateException("Selection failed");
    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}