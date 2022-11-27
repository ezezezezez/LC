import java.util.*;
import java.io.*;
import java.lang.*;

// 1171. Remove Zero Sum Consecutive Nodes from Linked List

public class P1171 {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode cur = head;
        List<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }


        while (true) {
            boolean flag = false;
            int sum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int n = list.size();
            List<ListNode> nxt = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                sum += list.get(i).val;
                if (map.containsKey(sum)) {
                    int idx = map.get(sum);
                    for (int j = 0; j <= idx; j++) {
                        nxt.add(list.get(j));
                    }
                    for (int j = i + 1; j < n; j++) {
                        nxt.add(list.get(j));
                    }
                    flag = true;
                    break;
                }
                map.putIfAbsent(sum, i);
            }
            if (!flag) break;
            list = nxt;
        }
        if (list.isEmpty()) return null;
        for (int i = 0; i < list.size() - 1; i++) list.get(i).next = list.get(i + 1);
        list.get(list.size() - 1).next = null;
        return list.get(0);
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
