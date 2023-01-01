import java.io.*;
import java.lang.*;
import java.util.*;

// 2336. Smallest Number in Infinite Set

public class P2336 {
    boolean[] used = new boolean[1001];
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public P2336() {
        Arrays.fill(used, true);
        for (int i = 1; i <= 1000; i++) pq.offer(i);
    }

    public int popSmallest() {
        int ret = pq.poll();
        used[ret] = false;
        return ret;
    }

    public void addBack(int num) {
        if (used[num]) return;
        used[num] = true;
        pq.offer(num);
    }
}
