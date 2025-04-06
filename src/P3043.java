import java.util.HashSet;
import java.util.Set;

// 3043. Find the Length of the Longest Common Prefix
public class P3043 {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int ret = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : arr1) {
            while (num > 0) {
                set.add(num);
                num /= 10;
            }
        }
        for (int num : arr2) {
            while (num > 0) {
                if (set.contains(num)) {
                    int x = 0, t = num;
                    while (t > 0) {
                        t /= 10;
                        x++;
                    }
                    ret = Math.max(ret, x);
                }
                num /= 10;
            }
        }
        return ret;
    }
}
