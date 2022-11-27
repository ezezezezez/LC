import java.util.*;
import java.io.*;
import java.lang.*;

// 1109. Corporate Flight Bookings

public class P1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int m = bookings.length;
        int[] ret = new int[n];

        int[] f = new int[n];
        for (int[] b : bookings) {
            int s = b[0] - 1, e = b[1] - 1;
            f[s] += b[2];
            if (e + 1 < n) f[e + 1] -= b[2];
        }
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += f[i];
            ret[i] = s;
        }

        return ret;
    }
}
