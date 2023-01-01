import java.io.*;
import java.lang.*;
import java.util.*;

// 2439. Minimize Maximum of Array

public class P2442 {
    public int countDistinctIntegers(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
            int c = 0;
            while (num > 0) {
                int d = num % 10;
                c = 10 * c + d;
                num /= 10;
            }
            set.add(c);
        }
        return set.size();
    }
}
