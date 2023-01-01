import java.io.*;
import java.lang.*;
import java.util.*;

// 1558. Minimum Numbers of Function Calls to Make Target Array

public class P1558 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int mc = 0;
        for (int num : nums) {
            int c = 0;
            while (num > 0) {
                if (num % 2 == 0) {
                    num /= 2;
                    c++;
                } else {
                    num--;
                    ret++;
                }
            }
            mc = Math.max(mc, c);
        }
        return ret + mc;
    }
}
