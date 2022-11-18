import java.util.*;
import java.io.*;
import java.lang.*;

// 984. String Without AAA or BBB

public class P985 {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] ret = new int[m];

        int sum = 0;
        for (int num : nums) {
            if (num % 2 == 0) sum += num;
        }

        for (int i = 0; i < m; i++) {
            int val = queries[i][0], index = queries[i][1];
            if (nums[index] % 2 == 0) {
                if ((nums[index] + val) % 2 == 0) {
                    nums[index] += val;
                    sum += val;
                    ret[i] = sum;
                } else {
                    sum -= nums[index];
                    nums[index] += val;
                    ret[i] = sum;
                }
            } else if ((nums[index] + val) % 2 == 0) {
                nums[index] += val;
                sum += nums[index];
                ret[i] = sum;
            } else {
                nums[index] += val;
                ret[i] = sum;
            }
        }

        return ret;
    }
}
