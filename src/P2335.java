import java.io.*;
import java.lang.*;
import java.util.*;

// 2335. Minimum Amount of Time to Fill Cups

public class P2335 {
    public int fillCups(int[] amount) {
        int n = amount.length;
        Arrays.sort(amount);
        if (amount[2] - amount[1] >= amount[0]) {
            return amount[2];
        }
        amount[0] -= amount[2] - amount[1];
        if (amount[0] % 2 == 0) {
            return amount[2] - amount[1] + amount[0] + (amount[1] - amount[0] / 2);
        }
        return amount[2] - amount[1] + amount[0] + (amount[1] - (amount[0] + 1) / 2) + 1;
    }

    public int fillCups2(int[] nums) {
        int n = nums.length;
        int mx = 0, sum = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
            sum += num;
        }
        return Math.max(mx, (sum + 1) / 2);
    }
}
