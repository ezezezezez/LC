import java.util.List;

// 2856. Minimum Array Length After Pair Removals
public class P2856 {
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int pre = 0, cnt = 0, mx = 0;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num != pre) cnt = 1;
            else cnt++;
            mx = Math.max(mx, cnt);
            pre = num;
        }
        if (mx * 2 > n) return n - 2 * (n - mx);
        return n % 2;
    }
}
