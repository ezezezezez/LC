import java.util.*;

// 2816. Double a Number Represented as a Linked List
public class P2816 {
    public class ListNode {
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

    public ListNode doubleIt(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        List<Integer> list2 = new ArrayList<>();
        int add = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int v = 2 * list.get(i) + add;
            add = v / 10;
            list2.add(v % 10);
        }
        if (add > 0) list2.add(add);
        ListNode ret = new ListNode();
        ListNode cur = ret;
        for (int i = list2.size() - 1; i >= 0; i--) {
            cur.next = new ListNode(list2.get(i));
            cur = cur.next;
        }
        return ret.next;
    }
}
