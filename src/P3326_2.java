// 3326. Minimum Division Operations to Make Array Non Decreasing

public class P3326_2 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        Map<Integer, Integer> mxD = new HashMap<>();

        for (int i = n - 2; i >= 0; i--) {
            int num = nums[i];
            while (num > nums[i + 1]) {
                if (mxD.containsKey(num)) num /= mxD.get(num);
                else {
                    boolean divided = false;
                    for (int j = 2; j * j <= num; j++) {
                        if (num % j == 0) {
                            mxD.put(num, num / j);
                            num /= num / j;
                            divided = true;
                            break;
                        }
                    }
                    if (!divided) return -1;
                }
                ret++;
            }
            nums[i] = num;
            // System.out.println(ret);
        }

        return ret;
    }
}
