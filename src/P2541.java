public class P2541 {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        if (k == 0) {
            for (int i = 0; i < n; i++) {
                if (nums1[i] != nums2[i]) return -1;
            }
            return 0;
        }
        long a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if ((nums1[i] - nums2[i]) % k != 0) return -1;
            if (nums1[i] - nums2[i] > 0) a += nums1[i] - nums2[i];
            else if (nums1[i] - nums2[i] < 0) b += nums1[i] - nums2[i];
        }
        return a + b == 0 ? a / k : -1;
    }
}
