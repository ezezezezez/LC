import java.io.*;
import java.lang.*;
import java.util.*;

// 1381. Design a Stack With Increment Operation

public class P1381 {
    Deque<Integer> s1 = new ArrayDeque<>();
    Deque<Integer> s2 = new ArrayDeque<>();
    int maxSize;

    public P1381(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (s1.size() < maxSize) s1.push(x);
    }

    public int pop() {
        return s1.isEmpty() ? -1 : s1.pop();
    }

    public void increment(int k, int val) {
        while (!s1.isEmpty()) s2.push(s1.pop());
        int sz = s2.size();
        for (int i = 0; i < Math.min(k, sz); i++) {
            s1.push(s2.pop() + val);
        }
        while (!s2.isEmpty()) s1.push(s2.pop());
    }
}
