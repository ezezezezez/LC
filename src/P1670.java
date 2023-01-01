import java.io.*;
import java.lang.*;
import java.util.*;

// 1670. Design Front Middle Back Queue

public class P1670 {
    Deque<Integer> front = new ArrayDeque<>();
    Deque<Integer> back = new ArrayDeque<>();

    public P1670() {

    }

    public void pushFront(int val) {
        front.offerFirst(val);
        if (front.size() - back.size() >= 2) {
            back.offerFirst(front.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (front.size() == back.size()) {
            front.offerLast(val);
        } else {
            back.offerFirst(front.pollLast());
            front.offerLast(val);
        }
    }

    public void pushBack(int val) {
        back.offerLast(val);
        if (back.size() > front.size()) {
            front.offerLast(back.pollFirst());
        }
    }

    public int popFront() {
        if (front.isEmpty()) return -1;
        int ret = front.pollFirst();
        if (front.size() < back.size()) {
            front.offerLast(back.pollFirst());
        }
        return ret;
    }

    public int popMiddle() {
        if (front.isEmpty()) return -1;
        int ret = front.pollLast();
        if (front.size() < back.size()) {
            front.offerLast(back.pollFirst());
        }
        return ret;
    }

    public int popBack() {
        if (front.isEmpty()) return -1;
        if (!back.isEmpty()) {
            int ret = back.pollLast();
            if (front.size() - back.size() >= 2) {
                back.offerFirst(front.pollLast());
            }
            return ret;
        } else {
            return front.pollFirst();
        }
    }
}
