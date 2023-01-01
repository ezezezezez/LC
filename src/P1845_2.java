import java.io.*;
import java.lang.*;
import java.util.*;

// 1845. Seat Reservation Manager

public class P1845_2 {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public P1845_2(int n) {
        for (int i = 1; i <= n; i++) {
            pq.offer(i);
        }
    }

    public int reserve() {
        return pq.poll();
    }

    public void unreserve(int seatNumber) {
        pq.offer(seatNumber);
    }
}
