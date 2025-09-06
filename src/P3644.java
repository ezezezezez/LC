// 3644. Maximum K to Sort a Permutation
public class P3644 {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int and = (1 << 30) - 1;
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) {
                sorted = false;
                and &= i;
            }
        }
        return sorted ? 0 : and;
    }
}
