import java.io.*;
import java.lang.*;
import java.util.*;

// 2226. Maximum Candies Allocated to K Children

public class P2226 {
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        int lo = 1, hi = (int)1e7;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            long c = 0;
            for (int candy : candies) {
                c += candy / mid;
            }
            if (c >= k) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t == -1 ? 0 : t;
    }
}
