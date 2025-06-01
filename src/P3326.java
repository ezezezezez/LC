// 3326. Minimum Division Operations to Make Array Non Decreasing

public class P3326 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int i = n - 2; i >= 0; i--) {
            int num = nums[i];
            while (num > nums[i + 1]) {
                boolean divided = false;
                for (int j = 2; j * j <= num; j++) {
                    if (num % j == 0) {
                        num /= num / j;
                        divided = true;
                        break;
                    }
                }

                if (!divided) return -1;
                ret++;
            }
            nums[i] = num;
        }

        return ret;
    }
}
