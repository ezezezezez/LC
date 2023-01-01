import java.io.*;
import java.lang.*;
import java.util.*;

// 1578. Minimum Time to Make Rope Colorful

public class P1578 {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int ret = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && colors.charAt(dq.peek()) == colors.charAt(i)) {
                if (neededTime[dq.peek()] < neededTime[i]) {
                    ret += neededTime[dq.pop()];
                    dq.push(i);
                } else {
                    ret += neededTime[i];
                }
            } else {
                dq.push(i);
            }
        }
        return ret;
    }
}
