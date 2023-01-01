import java.io.*;
import java.lang.*;
import java.util.*;

// 2455. Average Value of Even Numbers That Are Divisible by Three

public class P2455 {
    public int averageValue(int[] nums) {
        int n = nums.length;
        int sum = 0, cnt = 0;
        for (int num : nums) {
            if (num % 2 == 0 && num % 3 == 0) {
                sum += num;
                cnt++;
            }
        }
        return cnt == 0 ? 0 : sum / cnt;
    }
}
