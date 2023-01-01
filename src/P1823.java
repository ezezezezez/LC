import java.io.*;
import java.lang.*;
import java.util.*;

// 1823. Find the Winner of the Circular Game

public class P1823 {
    public int findTheWinner(int n, int k) {
        int counter = 0;
        boolean[] used = new boolean[n];
        int s = 0;
        while (counter < n - 1) {
            int cnt = 0;
            while (cnt < k) {
                if (!used[s]) {
                    cnt++;
                }
                s = (s + 1) % n;
            }
            used[Math.floorMod(s - 1, n)] = true;
            counter++;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                ret = i;
                break;
            }
        }
        return ret + 1;
    }
}
