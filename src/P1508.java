import java.io.*;
import java.lang.*;
import java.util.*;

// 1504. Count Submatrices With All Ones


// TODO: take a look at the O(Nlog(S)) solution
public class P1508 {
    int mod = (int)(1e9 + 7);

    // O(N^2log(N))
    public int rangeSum(int[] nums, int n, int left, int right) {
        int ret = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                list.add(sum);
            }
        }
        Collections.sort(list);
        for (int i = left - 1; i <= right - 1; i++) {
            ret = (ret + list.get(i)) % mod;
        }
        return ret;
    }

    // O(N^2log(N))
    public int rangeSum2(int[] nums, int n, int left, int right) {
        int ret = 0;
        int[] sums = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                sums[idx++] = sum;
            }
        }
        Arrays.sort(sums);
        for (int i = left - 1; i <= right - 1; i++) {
            ret = (ret + sums[i]) % mod;
        }
        return ret;
    }

    // O(N^2log(N))
    public int rangeSum3(int[] nums, int n, int left, int right) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        int ret = 0;
        int cnt = 0;
        while (cnt++ < right) {
            int[] node = pq.poll();
            int sum = node[0], idx = node[1];
            if (cnt >= left) ret = (ret + sum) % mod;
            if (idx + 1 < n) pq.offer(new int[]{sum + nums[idx + 1], idx + 1});
        }
        return ret;
    }
}
