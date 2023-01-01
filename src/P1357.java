import java.io.*;
import java.lang.*;
import java.util.*;

// 1352. Product of the Last K Numbers

public class P1357 {
    int n, discount;
    Map<Integer, Integer> map = new HashMap<>();
    int cur;

    public P1357(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        for (int i = 0; i < products.length; i++) {
            map.put(products[i], prices[i]);
        }
    }

    public double getBill(int[] products, int[] amounts) {
        int tot = 0;
        for (int i = 0; i < products.length; i++) {
            int product = products[i];
            tot += map.get(product) * amounts[i];
        }
        cur++;
        if (cur == n) {
            cur = 0;
            return tot * (100.0 - discount) / 100;
        }
        return tot;
    }
}
