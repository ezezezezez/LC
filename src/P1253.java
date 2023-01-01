import java.util.*;
import java.io.*;
import java.lang.*;

// 1253. Reconstruct a 2-Row Binary Matrix

public class P1253 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        ret.add(new ArrayList<>());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                ret.get(i).add(0);
            }
        }

        int up = 0, lo = 0;
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                ret.get(0).set(i, 1); up++;
                ret.get(1).set(i, 1); lo++;
            }
        }

        int upRem = upper - up, loRem = lower - lo;
        if (upRem < 0 || loRem < 0) return List.of();
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1) {
                if (upRem > 0) {
                    upRem--;
                    ret.get(0).set(i, 1);
                } else if (loRem > 0) {
                    loRem--;
                    ret.get(1).set(i, 1);
                } else {
                    return List.of();
                }
            }
        }

        return upRem == 0 && loRem == 0 ? ret : List.of();
    }
}
