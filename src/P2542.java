import java.io.*;
import java.lang.*;
import java.util.*;

public class P2542 {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        Arrays.sort(arr, Comparator.comparingInt(a -> nums2[a]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < k - 1; i++) {
            int num = nums1[arr[n - 1 - i]];
            sum += num;
            pq.offer(num);
        }
        long max = 0;
        for (int i = n - k; i >= 0; i--) {
            int t = nums2[arr[i]];
            int x = nums1[arr[i]];
            sum += x;
            max = Math.max(max, t * sum);
            pq.offer(x);
            sum -= pq.poll();
        }
        return max;
    }
}
