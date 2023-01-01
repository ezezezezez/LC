import java.io.*;
import java.lang.*;
import java.util.*;

// 1833. Maximum Ice Cream Bars

public class P1833 {
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        Arrays.sort(costs);
        int idx = 0;
        while (idx < n && coins >= costs[idx]) {
            coins -= costs[idx++];
        }
        return idx;
    }
}
