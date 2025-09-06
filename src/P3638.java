// 3638. Maximum Balanced Shipments

public class P3638 {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        int ret = 0;
        int mx = 0;
        for (int i = 0; i < n; i++) {
            if (weight[i] < mx) {
                ret++;
                mx = 0;
            } else {
                mx = Math.max(mx, weight[i]);
            }
        }

        return ret;
    }
}
