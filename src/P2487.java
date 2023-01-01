import java.io.*;
import java.lang.*;
import java.util.*;

// 2487. Remove Nodes From Linked List

public class P2487 {
    public ListNode removeNodes(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int mx = list.get(list.size() - 1).val;
        boolean[] toRemove = new boolean[list.size()];
        for (int i = list.size() - 2; i >= 0; i--) {
            if (mx > list.get(i).val) {
                toRemove[i] = true;
            }
            mx = Math.max(mx, list.get(i).val);
        }
        ListNode d = new ListNode();
        ListNode ret = d;
        for (int i = 0; i < list.size(); i++) {
            if (toRemove[i]) continue;
            d.next = list.get(i);
            d = d.next;
        }
        return ret.next;
    }

    class ListNode {
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
