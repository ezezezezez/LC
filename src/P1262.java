import java.util.*;
import java.io.*;
import java.lang.*;

// 1262. Greatest Sum Divisible by Three

public class P1262 {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        List<Integer> mod1 = new ArrayList<>();
        List<Integer> mod2 = new ArrayList<>();
        int sum = 0;
        int rem = 0;
        for (int num : nums) {
            if (num % 3 == 0) sum += num;
            else if (num % 3 == 1) {
                rem += num;
                mod1.add(num);
            } else {
                rem += num;
                mod2.add(num);
            }
        }
        if (rem % 3 == 0) return sum + rem;
        Collections.sort(mod1);
        Collections.sort(mod2);
        if (rem % 3 == 1) {
            int a = 0, b = 0;
            if (mod1.size() >= 1) {
                a = rem - mod1.get(0);
            }
            if (mod2.size() >= 2) {
                b = rem - mod2.get(0) - mod2.get(1);
            }
            return sum + Math.max(a, b);
        } else {
            int a = 0, b = 0;
            if (mod1.size() >= 2) {
                a = rem - mod1.get(0) - mod1.get(1);
            }
            if (mod2.size() >= 1) {
                b = rem - mod2.get(0);
            }
            return sum + Math.max(a, b);
        }
    }

    public int maxSumDivThree2(int[] nums) {
        int n = nums.length;
        int[] f = new int[3];
        for (int i = 0; i < n; i++) {
            int a = f[0] + nums[i];
            int b = f[1] + nums[i];
            int c = f[2] + nums[i];

            f[a % 3] = Math.max(f[a % 3], a);
            f[b % 3] = Math.max(f[b % 3], b);
            f[c % 3] = Math.max(f[c % 3], c);
        }

        return f[0];
    }
}
