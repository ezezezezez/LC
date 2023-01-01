import java.io.*;
import java.lang.*;
import java.util.*;

// 1669. Merge In Between Linked Lists

public class P1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = list1;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        if (a == 0) {
            cur = list2;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = b == list.size() - 1 ? null : list.get(b + 1);
            return list2;
        } else {
            list.get(a - 1).next = list2;
            cur = list2;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = b == list.size() - 1 ? null : list.get(b + 1);
            return list.get(0);
        }
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
