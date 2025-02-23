import java.util.ArrayList;
import java.util.List;

// 2807. Insert Greatest Common Divisors in Linked List
public class P2807 {
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

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        ListNode ret = new ListNode(list.get(0).val);
        ListNode pre = ret;
        for (int i = 1; i < list.size(); i++) {
            ListNode mid = new ListNode(gcd(list.get(i - 1).val, list.get(i).val));
            ListNode cur = new ListNode(list.get(i).val);
            pre.next = mid;
            mid.next = cur;
            pre = cur;
        }
        return ret;
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
