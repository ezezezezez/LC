import java.util.*;
import java.io.*;
import java.lang.*;

// 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

public class P1343 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (i >= k) sum -= arr[i - k];
            if (i >= k - 1) {
                cnt += sum * 1.0 / k >= threshold ? 1 : 0;
            }
        }

        return cnt;
    }
}
