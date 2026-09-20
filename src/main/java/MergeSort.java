public class MergeSort {

    private static final int Cutoff = 15;
    public static void sort(int[] a, Metrics metrics) {
        if (a.length <= 1) {
            return;
        }
        int[] buffer = new int[a.length];
        mergeSort(a, buffer, 0, a.length - 1, metrics, 1);
    }
    private static void mergeSort(int[] a, int[] buffer, int left, int right, Metrics metrics, int depth) {
        metrics.updateDepth(depth);
        if (right - left + 1 <= Cutoff) {
            insertionSort(a, left, right, metrics);
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(a, buffer, left, mid, metrics, depth + 1);
        mergeSort(a, buffer, mid + 1, right, metrics, depth + 1);
        merge(a, buffer, left, mid, right, metrics);
    }
    private static void merge(int[] a, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            metrics.compare();
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }
        while (i <= mid) {
            buffer[k++] = a[i++];
        }
        while (j <= right) {
            buffer[k++] = a[j++];
        }
        for (int x = left; x <= right; x++) {
            a[x] = buffer[x];
        }
    }
    private static void insertionSort(
            int[] a,
            int left,
            int right,
            Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int value = a[i];
            int j = i - 1;
            while (j >= left) {
                metrics.compare();
                if (a[j] <= value) {
                    break;
                }
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = value;
        }
    }
}