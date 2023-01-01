import java.io.*;
import java.lang.*;
import java.util.*;

// 2517. Maximum Tastiness of Candy Basket

public class P2517 {
    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);
        int lo = 0, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int pre = price[0], cnt = 1;
            int mn = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                if (price[i] - pre >= mid) {
                    mn = Math.min(mn, price[i] - pre);
                    pre = price[i];
                    cnt++;
                    if (cnt == k) break;
                }
            }
            if (cnt == k) {
                t = mn;
                lo = mn + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
