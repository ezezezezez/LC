import java.io.*;
import java.lang.*;
import java.util.*;

// 1845. Seat Reservation Manager

public class P1845 {
    boolean[] seats;
    int idx;

    public P1845(int n) {
        seats = new boolean[n];
    }

    public int reserve() {
        // System.out.println(idx);
        seats[idx] = true;
        int ret = idx + 1;
        while (idx < seats.length && seats[idx]) idx++;
        return ret;
    }

    public void unreserve(int seatNumber) {
        seats[seatNumber - 1] = false;
        if (seatNumber - 1 < idx) {
            idx = seatNumber - 1;
        }
    }
}
