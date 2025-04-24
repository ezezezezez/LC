import java.util.HashSet;
import java.util.Set;

// 3217. Delete Nodes From Linked List Present in Array
public class P3217 {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        ListNode ret = new ListNode();
        ListNode cur = ret;
        while (head != null) {
            if (!set.contains(head.val)) {
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        cur.next = null;
        return ret.next;
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
