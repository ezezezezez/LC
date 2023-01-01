import java.io.*;
import java.lang.*;
import java.util.*;

// 1386. Cinema Seat Allocation

public class P1386 {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0], col = seat[1];
            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }
        for (int row : map.keySet()) {
            Set<Integer> cols = map.get(row);
        }
        int ret = 0;
        int rem = n - map.size();
        ret += 2 * rem;
        for (int row : map.keySet()) {
            Set<Integer> cols = map.get(row);
            int cnt = 0;
            for (int i = 1; i <= 10; i++) {
                if (cols.contains(i)) {
                    cnt = 0;
                } else {
                    cnt++;
                    if (cnt == 5) cnt--;
                    if (cnt == 4) {
                        if (i == 5 || i == 7 || i == 9) {
                            ret++;
                            cnt = 0;
                        }
                    }
                }
            }
        }

        return ret;
    }
}
