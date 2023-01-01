import java.io.*;
import java.lang.*;
import java.util.*;

// 1942. The Number of the Smallest Unoccupied Chair

public class P1942 {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] events = new int[2 * n][2]; // 0 leave, 1 come
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int[] time = times[i];
            events[idx++] = new int[]{time[0], 1, i};
            events[idx++] = new int[]{time[1], 0, i};
        }
        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) pq.offer(i);
        int[] seats = new int[n];
        for (int i = 0; i < events.length; i++) {
            int time = events[i][0], type = events[i][1], friend = events[i][2];
            if (type == 0) {
                int seat = seats[friend];
                pq.offer(seat);
            } else {
                if (friend == targetFriend) return pq.peek();
                seats[friend] = pq.poll();
            }
        }
        return -1;
    }
}
