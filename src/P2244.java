import java.io.*;
import java.lang.*;
import java.util.*;

// 2244. Minimum Rounds to Complete All Tasks

public class P2244 {
    public int minimumRounds(int[] tasks) {
        int n = tasks.length;
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.merge(task, 1, Integer::sum);
        }
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            if (cnt % 3 == 0) {
                ret += cnt / 3;
            } else if (cnt % 3 == 1) {
                if (cnt == 1) return -1;
                ret += 2;
                cnt -= 4;
                ret += cnt / 3;
            } else {
                ret += cnt / 3 + 1;
            }
        }
        return ret;
    }
}
