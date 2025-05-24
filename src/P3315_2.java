import java.util.List;

// 3315. Construct the Minimum Bitwise Array II
public class P3315_2 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num % 2 == 0) {
                ret[i] = -1;
            } else {
                int t = ~num;
                int lb = t & -t;
                ret[i] = num ^ (lb >> 1);
            }
        }
        return ret;
    }
}
