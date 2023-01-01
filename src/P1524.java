import java.io.*;
import java.lang.*;
import java.util.*;

// 1524. Number of Sub-arrays With Odd Sum

public class P1524 {
    int mod = (int)(1e9 + 7);
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int ret = 0;
        int odd = 0, even = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum % 2 == 0) {
                ret = (ret + odd) % mod;
                even++;
            } else {
                ret = (ret + even) % mod;
                odd++;
            }
        }
        return ret;
    }
}
