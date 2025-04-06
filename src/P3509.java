import java.util.*;

// 3509. Maximum Product of Subsequences With an Alternating Sum Equal to K
public class P3509 {
    Set<Data> set = new HashSet<>();
    int n;
    int[] nums;
    int k;
    int limit;

    int ret = -1;
    public int maxProduct(int[] nums, int k, int limit) {
        this.n = nums.length;
        this.nums = nums;
        this.k = k;
        this.limit = limit;

        int mx = Arrays.stream(nums).max().getAsInt();
        if (k > (n + 1) / 2 * mx) return ret;
        if (k < -n / 2 * mx) return ret;

        dfs(0, 0, 1, true, true);
        return ret;
    }

    void dfs(int i, int sum, int prod, boolean positive, boolean empty) {
        Data data = new Data(i, sum, prod, positive, empty);
        if (set.contains(data)) return;
        set.add(data);
        if (prod > limit) {
            prod = -1;
        }

        if (i == n) {
            if (!empty && sum == k && prod >= 0) {
                ret = Math.max(ret, prod);
            }
            return;
        }

        dfs(i + 1, sum, prod, positive, empty);

        sum += positive ? nums[i] : -nums[i];
        dfs(i + 1, sum, prod * nums[i] < 0 ? -1 : prod * nums[i], !positive, false);
    }

    class Data {
        int i;
        int sum;
        int prod;

        Data(int i, int sum, int prod, boolean positive, boolean empty) {
            this.i = i;
            this.sum = sum;
            this.prod = prod;
            this.positive = positive;
            this.empty = empty;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;
            return i == data.i && sum == data.sum && prod == data.prod && positive == data.positive && empty == data.empty;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + sum;
            result = 31 * result + prod;
            result = 31 * result + Boolean.hashCode(positive);
            result = 31 * result + Boolean.hashCode(empty);
            return result;
        }

        boolean positive;
        boolean empty;
    }
}
