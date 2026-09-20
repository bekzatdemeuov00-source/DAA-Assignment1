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

            int[] partition =
                    QuickSort.partition(a, left, right, metrics);

            int lt = partition[0];
            int gt = partition[1];

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
}