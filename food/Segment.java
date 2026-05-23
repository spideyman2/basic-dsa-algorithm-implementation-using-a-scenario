package food;

public class Segment {
    private double[] tree;
    private int n;

    public Segment(int n) {
        this.n = n;
        if (n > 0) {
            tree = new double[4 * n];
        }
    }

    public void addRevenue(int zoneIndex, double amount) {
        if (n > 0 && zoneIndex >= 0 && zoneIndex < n) {
            update(0, 0, n - 1, zoneIndex, amount);
        }
    }

    private void update(int node, int start, int end, int idx, double val) {
        if (start == end) {
            tree[node] += val;
            return;
        }
        int mid = (start + end) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public double getRevenueRange(int l, int r) {
        if (n == 0 || l > r || l < 0 || r >= n) {
            return 0.0;
        }
        return query(0, 0, n - 1, l, r);
    }

    private double query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0.0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        double leftChild = query(2 * node + 1, start, mid, l, r);
        double rightChild = query(2 * node + 2, mid + 1, end, l, r);
        return leftChild + rightChild;
    }
}