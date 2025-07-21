// 421. Maximum XOR of Two Numbers in an Array

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P421 {
    public int findMaximumXOR(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int len = Integer.toBinaryString(max).length();
        int mx = 0, mask = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = len - 1; i >= 0; i--) {
            int bit = 1 << i;
            mask |= bit;
            for (int num : nums) set.add(mask & num);
            int hope = mx | bit;
            for (int prefix : set) {
                int complement = hope ^ prefix;
                if (set.contains(complement)) {
                    mx = hope;
                    break;
                }
            }
            set.clear();
        }
        return mx;
    }
}
