import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3646. Next Special Palindrome Number
public class P3646 {
    static List<Long> nums = new ArrayList<>();

    static {
        for (int i = 1; i <= 9; i += 2) {
            int[] d = new int[10];
            d[0] = i;
            int[] rem = new int[10];
            for (int j = 2; j <= 8; j += 2) {
                rem[j] = j;
            }
            rem[i] = i - 1;
            fill1(d, 1, rem);
            // System.out.println("1. " + nums);
        }
        for (int i = 2; i <= 8; i += 2) {
            int[] d = new int[10];
            d[0] = i;
            int[] rem = new int[10];
            for (int j = 2; j <= 8; j += 2) {
                rem[j] = j;
            }
            rem[i] -= 2;
            fill2(d, 1, rem);
            // System.out.println("2. " + nums);
        }
    }

    static void fill1(int[] d, int idx, int[] rem) {
        if (valid1(d, idx)) nums.add(build1(d, idx));
        if (idx >= 8) return;
        for (int i = 1; i <= 9; i++) {
            if (rem[i] > 0) {
                rem[i] -= 2;
                d[idx] = i;
                fill1(d, idx + 1, rem);
                rem[i] += 2;
            }
        }
    }

    static void fill2(int[] d, int idx, int[] rem) {
        if (valid2(d, idx)) nums.add(build2(d, idx));
        if (idx >= 8) return;
        for (int i = 2; i <= 8; i += 2) {
            if (rem[i] > 0) {
                rem[i] -= 2;
                d[idx] = i;
                fill2(d, idx + 1, rem);
                rem[i] += 2;
            }
        }
    }

    static boolean valid1(int[] d, int idx) {
        int[] cnt = new int[10];
        for (int i = 0; i < idx; i++) {
            cnt[d[i]] = d[i];
        }
        for (int i = 0; i < idx; i++) {
            if (i == 0) {
                cnt[d[i]]--;
                continue;
            }
            cnt[d[i]] -= 2;
        }
        for (int c : cnt) {
            if (c > 0) return false;
        }
        return true;
    }

    static boolean valid2(int[] d, int idx) {
        int[] cnt = new int[10];
        for (int i = 0; i < idx; i++) {
            cnt[d[i]] = d[i];
        }
        for (int i = 0; i < idx; i++) {
            cnt[d[i]] -= 2;
        }
        for (int c : cnt) {
            if (c > 0) return false;
        }
        return true;
    }

    static long build1(int[] d, int idx) {
        long ret = 0;
        for (int i = idx - 1; i > 0; i--) {
            ret = 10 * ret + d[i];
        }
        for (int i = 0; i < idx; i++) {
            ret = 10 * ret + d[i];
        }
        return ret;
    }

    static long build2(int[] d, int idx) {
        long ret = 0;
        for (int i = idx - 1; i >= 0; i--) {
            ret = 10 * ret + d[i];
        }
        for (int i = 0; i < idx; i++) {
            ret = 10 * ret + d[i];
        }
        return ret;
    }

    public long specialPalindrome(long n) {
        Collections.sort(nums);
        // System.out.println(nums.size());
        // System.out.println(nums);
        for (long num : nums) {
            if (num > n) return num;
        }
        return -1;
    }
}
