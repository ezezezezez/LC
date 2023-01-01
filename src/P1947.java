import java.io.*;
import java.lang.*;
import java.util.*;

// 1947. Maximum Compatibility Score Sum

public class P1947 {
    int ret;
    int m, n;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        m = students.length;
        n = students[0].length;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < m; i++) dq.offer(i);
        dfs(students, mentors, dq, new ArrayList<>());
        return ret;
    }

    void dfs(int[][] students, int[][] mentors, Deque<Integer> dq, List<Integer> list) {
        if (list.size() == m) {
            int score = 0;
            for (int i = 0; i < m; i++) {
                int idx = list.get(i);
                for (int j = 0; j < n; j++) {
                    if (students[i][j] == mentors[idx][j]) score++;
                }
            }
            ret = Math.max(ret, score);
            return;
        }
        int sz = dq.size();
        for (int i = 0; i < sz; i++) {
            int cur = dq.poll();
            list.add(cur);
            dfs(students, mentors, dq, list);
            list.remove(list.size() - 1);
            dq.offer(cur);
        }
    }
}
