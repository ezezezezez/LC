import java.io.*;
import java.lang.*;
import java.util.*;

// 1711. Count Good Meals

public class P1711 {
    public int countPairs(int[] de) {
        int n = de.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(de[0], 1);
        int mod = (int)(1e9 + 7);
        int ret = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 31; j++) {
                int sum = 1 << j;
                if (map.containsKey(sum - de[i])) {
                    ret = (ret + map.get(sum - de[i])) % mod;
                }
            }
            map.merge(de[i], 1, Integer::sum);
        }
        return ret;
    }
}
