import java.io.*;
import java.lang.*;
import java.util.*;

// 2326. Spiral Matrix IV

public class P2326 {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ret = new int[m][n];
        for (int[] row : ret) Arrays.fill(row, -1);
        int x = 0, y = -1;
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        int dirIdx = 1;
        ListNode cur = head;
        while (cur != null) {
            int val = cur.val;
            int nx = x + dir[dirIdx], ny = y + dir[dirIdx + 1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || ret[nx][ny] != -1) {
                dirIdx = (dirIdx + 1) % 4;
                nx = x + dir[dirIdx];
                ny = y + dir[dirIdx + 1];
            }
            ret[nx][ny] = val;
            x = nx;
            y = ny;
            cur = cur.next;
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
