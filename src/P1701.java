import java.io.*;
import java.lang.*;
import java.util.*;

// 1701. Average Waiting Time

public class P1701 {
    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        double tot = 0;
        int cur = 0;
        for (int[] customer : customers) {
            if (cur <= customer[0]) {
                tot += customer[1];
                cur = customer[0] + customer[1];
            } else {
                tot += cur + customer[1] - customer[0];
                cur += customer[1];
            }
        }
        return tot / n;
    }
}
