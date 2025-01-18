import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P2542_2 {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        Arrays.sort(arr, Comparator.comparingInt(a -> nums2[a]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int num = nums1[arr[n - 1 - i]];
            sum += num;
            pq.offer(num);
        }
        long max = sum * nums2[arr[n - k]];
        for (int i = n - k - 1; i >= 0; i--) {
            int t = nums2[arr[i]];
            int x = nums1[arr[i]];
            if (x > pq.peek()) {
                sum += x;
                pq.offer(x);
                sum -= pq.poll();
                max = Math.max(max, sum * t);
            }
        }
        return max;
    }
}
