import java.io.*;
import java.lang.*;
import java.util.*;

// 2365. Task Scheduler II

public class P2365 {
    public long taskSchedulerII(int[] tasks, int space) {
        int n = tasks.length;
        long ret = 0;
        long cur = 0;
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int task = tasks[i];
            if (map.containsKey(task) && cur < map.get(task)) {
                cur = map.get(task);
            } else {
                cur++;
            }
            map.put(task, cur + space + 1);
        }
        return cur;
    }
}
