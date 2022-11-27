import java.util.*;
import java.io.*;
import java.lang.*;

// 1104. Path In Zigzag Labelled Binary Tree

public class P1104 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> ret = new ArrayList<>();
        if (label == 1) {
            ret.add(1);
            return ret;
        }

        int[] map = new int[label + 1];
        int h = 0, idx = 2;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);

        boolean flag = false;
        while (idx <= label) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                if (h % 2 == 0) {
                    int cur = q.pollLast();
                    q.offerFirst(idx);
                    map[idx++] = cur;
                    if (idx - 1 == label) {
                        flag = true;
                        break;
                    }
                    q.offerFirst(idx);
                    map[idx++] = cur;
                    if (idx - 1 == label) {
                        flag = true;
                        break;
                    }
                } else {
                    int cur = q.pollFirst();
                    q.offerLast(idx);
                    map[idx++] = cur;
                    if (idx - 1 == label) {
                        flag = true;
                        break;
                    }
                    q.offerLast(idx);
                    map[idx++] = cur;
                    if (idx - 1 == label) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
            h++;
        }

        int s = label;
        while (s != 1) {
            ret.add(s);
            s = map[s];
        }
        ret.add(1);
        Collections.reverse(ret);

        return ret;
    }
}
