import java.io.*;
import java.lang.*;
import java.util.*;

// 1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers

public class P1577 {
    public int numTriplets(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int ret = 0;
        for (int i = 0; i < n1; i++) {
            long a = nums1[i];
            Map<Long, Integer> map = new HashMap<>();
            map.put((long)nums2[0], 1);
            for (int j = 1; j < n2; j++) {
                long c = nums2[j];
                if (a * a % c == 0) {
                    long b = a * a / c;
                    ret += map.getOrDefault(b, 0);
                }
                map.merge(c, 1, Integer::sum);
            }
        }
        // System.out.println(ret);
        for (int i = 0; i < n2; i++) {
            long a = nums2[i];
            Map<Long, Integer> map = new HashMap<>();
            map.put((long)nums1[0], 1);
            for (int j = 1; j < n1; j++) {
                long c = nums1[j];
                if (a * a % c == 0) {
                    long b = a * a / c;
                    ret += map.getOrDefault(b, 0);
                }
                map.merge(c, 1, Integer::sum);
            }
        }
        return ret;
    }
}
