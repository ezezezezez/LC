// 421. Maximum XOR of Two Numbers in an Array

import java.util.HashSet;
import java.util.Set;

public class P421_2 {
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int mx = 0;
        for (int num : nums) mx = Math.max(mx, num);
        int j = 30;
        for (; j >= 0; j--) {
            if (((1 << j) & mx) > 0) {
                break;
            }
        }
        if (j == -1) return 0;
        int mask = 0;
        int curMx = 0;
        for (int i = j; i >= 0; i--) {
            mask |= (1 << i);
            int tempMx = curMx | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) set.add(num & mask);
            for (int p : set) {
                int q = tempMx ^ p;
                if (set.contains(q)) {
                    curMx = tempMx;
                    break;
                }
            }
        }
        return curMx;
    }
}
