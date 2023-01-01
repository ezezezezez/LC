import java.io.*;
import java.lang.*;
import java.util.*;

// 2293. Min Max Game

public class P2293 {
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            int[] nxt = new int[n / 2];
            for (int i = 0; i < n / 2; i++) {
                if (i % 2 == 0) {
                    nxt[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nxt[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
            nums = nxt;
            n /= 2;
        }
        return nums[0];
    }
}
