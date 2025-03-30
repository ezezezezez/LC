import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2934. Minimum Operations to Maximize Last Elements in Arrays
public class P2934 {
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ret1 = 0;
        boolean valid1 = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums1[i] > nums1[n - 1] || nums2[i] > nums2[n - 1]) {
                if (nums2[i] > nums1[n - 1] || nums1[i] > nums2[n - 1]) {
                    valid1 = false;
                    break;
                }
                ret1++;
            }
        }
        int t = nums1[n - 1];
        nums1[n - 1] = nums2[n - 1];
        nums2[n - 1] = t;
        int ret2 = 1;
        boolean valid2 = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums1[i] > nums1[n - 1] || nums2[i] > nums2[n - 1]) {
                if (nums2[i] > nums1[n - 1] || nums1[i] > nums2[n - 1]) {
                    valid2 = false;
                    break;
                }
                ret2++;
            }
        }
        if (!valid1 && !valid2) return -1;
        return Math.min(ret1, ret2);
    }
}
