import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void sort(int[] a, Metrics metrics) {
        quickSort(a, 0, a.length - 1, metrics, 1);
    }

    private static void quickSort(int[] a, int left, int right,
                                  Metrics metrics, int depth) {
        metrics.updateDepth(depth);

        while (left < right) {

            int[] partition = partition(a, left, right, metrics);
            int lt = partition[0];
            int gt = partition[1];

            int leftSize = lt - left;
            int rightSize = right - gt;

            if (leftSize < rightSize) {
                if (left < lt - 1) {
                    quickSort(a, left, lt - 1, metrics, depth + 1);
                }

                left = gt + 1;
            } else {
                if (gt + 1 < right) {
                    quickSort(a, gt + 1, right, metrics, depth + 1);
                }

                right = lt - 1;
            }
        }
    }

    public static int[] partition(int[] a, int left, int right,
                                  Metrics metrics) {

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

        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}