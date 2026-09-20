public class Metrics {
    public long comparisons = 0;
    public int maxDepth = 0;

    public void compare() {
        comparisons++;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
    }
}