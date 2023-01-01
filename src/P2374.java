import java.io.*;
import java.lang.*;
import java.util.*;

// 2374. Node With Highest Edge Score

public class P2374 {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] degs = new long[n];
        for (int i = 0; i < n; i++) {
            degs[edges[i]] += i;
        }
        long mx = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (degs[i] > mx) {
                mx = degs[i];
                idx = i;
            }
        }
        return idx;
    }
}
