import java.io.*;
import java.lang.*;
import java.util.*;

// 2332. The Latest Time to Catch a Bus

public class P2332 {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int n = buses.length, m = passengers.length;
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int pre = 0;
        int ret = Math.min(buses[0], passengers[0] - 1);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            int bus = buses[i];
            while (j < m && passengers[j] <= bus && dq.size() < capacity) {
                if (passengers[j] - pre > 1) {
                    ret = passengers[j] - 1;
                }
                dq.offer(passengers[j]);
                pre = passengers[j];
                j++;
            }
            // System.out.println(dq);
            if (dq.size() < capacity && (dq.isEmpty() || dq.peekLast() < bus)) ret = Math.max(ret, bus);
            while (!dq.isEmpty()) dq.poll();
        }
        return ret;
    }
}
