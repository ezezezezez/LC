import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

// 1124. Longest Well-Performing Interval

public class P1124 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        for (int i = 0; i < n; i++) {
            hours[i] = hours[i] > 8 ? 1 : -1;
        }
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + hours[i];
        }

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            if (dq.isEmpty() || prefix[i] < prefix[dq.peek()]) {
                dq.push(i);
            }
        }

        int ret = 0;
        int j = n;
        while (!dq.isEmpty()) {
            if (j == dq.peek()) dq.pop();
            else {
                while (!dq.isEmpty() && prefix[dq.peek()] < prefix[j]) {
                    ret = Math.max(ret, j - dq.pop());
                }
            }
            j--;
        }

        return ret;
    }
}
