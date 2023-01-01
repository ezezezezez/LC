import java.io.*;
import java.lang.*;
import java.util.*;

// 1472. Design Browser History

public class P1472 {
    Deque<String> backward = new ArrayDeque<>();
    Deque<String> forward = new ArrayDeque<>();

    public P1472(String homepage) {
        backward.push(homepage);
    }

    public void visit(String url) {
        forward.clear();
        backward.push(url);
    }

    public String back(int steps) {
        int sz = Math.min(steps, backward.size() - 1);
        while (sz-- > 0) {
            forward.push(backward.pop());
        }
        return backward.peek();
    }

    public String forward(int steps) {
        int sz = Math.min(steps, forward.size());
        while (sz-- > 0) {
            backward.push(forward.pop());
        }
        return backward.peek();
    }
}
