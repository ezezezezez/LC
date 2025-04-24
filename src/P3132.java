import java.util.Arrays;

// 3132. Find the Integer Added to Array II
public class P3132 {
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ret = Integer.MAX_VALUE;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int s1 = 0;
                while (s1 == i || s1 == j) s1++;
                int diff = nums2[0] - nums1[s1];
                // System.out.println(s1 + " " + diff);
                s1++;
                boolean valid = true;
                for (int k = 1; k < nums2.length; k++) {
                    while (s1 == i || s1 == j) s1++;
                    // System.out.println(s1 + " " + (nums2[k] - nums1[s1]));
                    if (nums2[k] - nums1[s1] != diff) {
                        valid = false;
                        break;
                    }
                    s1++;
                }
                if (valid) {
                    ret = Math.min(ret, diff);
                }
            }
        }
        return ret;
    }
}
