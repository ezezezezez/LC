import java.util.*;
import java.io.*;
import java.lang.*;

// 1019. Next Greater Node In Linked List

public class P1019 {
    public int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int n = list.size();
        int[] ret = new int[n];

        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(0);
        for (int i = 1; i < n; i++) {
            while (!dq.isEmpty() && list.get(dq.peek()) < list.get(i)) {
                ret[dq.pop()] = list.get(i);
            }
            dq.push(i);
        }

        return ret;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
