import java.util.List;

// 2845. Count of Interesting Subarrays
public class P2845 {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long ret = 0;
        int[] groups = new int[n];
        int curGroup = 0;
        int cnt = 0, pre = 0;
        int lastK = -1;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i) % modulo;
            if (num == k) {
                groups[curGroup] += cnt + 1;
                cnt = 0;
            } else {
                cnt++;
            }
            if (num == k) {
                int checkGroup = (curGroup - k + 1 + modulo) % modulo;
                if (checkGroup < n) {
                    ret += groups[checkGroup];
                    pre = groups[checkGroup];
                }
                curGroup = (curGroup + 1) % modulo;
                lastK = i;
            } else {
                if (k == 0) {
                    ret += i - lastK;
                }
                ret += pre;
            }
            // System.out.println(i + " " + ret);
        }
        // System.out.println(Arrays.toString(groups));
        return ret;
    }
}
