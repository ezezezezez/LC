import java.util.*;
import java.io.*;
import java.lang.*;

// 904. Fruit Into Baskets
public class P904 {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        int ret = 0;

        for (int i = 0, j = 0; i < n; i++) {
            cnt.merge(fruits[i], 1, Integer::sum);
            if (cnt.size() <= 2) {
                ret = Math.max(ret, i - j + 1);
                continue;
            }
            while (j < n && cnt.size() > 2) {
                cnt.merge(fruits[j], -1, Integer::sum);
                if (cnt.get(fruits[j]) == 0) {
                    cnt.remove(fruits[j]);
                }
                j++;
            }
            ret = Math.max(ret, i - j + 1);
        }

        return ret;
    }
}
