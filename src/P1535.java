import java.io.*;
import java.lang.*;
import java.util.*;

// 1535. Find the Winner of an Array Game

public class P1535 {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        if (k >= n) {
            int mx = arr[0];
            for (int num : arr) mx = Math.max(mx, num);
            return mx;
        }
        List<Integer> list = new LinkedList<>();
        for (int num : arr) list.add(num);
        int lastWin = -1;
        int winCnt = 0;
        while (winCnt < k) {
            if (list.get(0) > list.get(1)) {
                int num = list.get(1);
                winCnt = lastWin == list.get(0) ? winCnt + 1 : 1;
                lastWin = list.get(0);
                list.remove(1);
                list.add(num);
            } else {
                int num = list.get(0);
                winCnt = lastWin == list.get(1) ? winCnt + 1 : 1;
                lastWin = list.get(1);
                list.remove(0);
                list.add(num);
            }
            // System.out.println(list);
        }
        return list.get(0);
    }

    public int getWinner2(int[] arr, int k) {
        int n = arr.length;
        int res = arr[0];
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > res) {
                res = arr[i];
                count = 1;
            } else {
                count++;
            }
            if (count == k) return res;
        }
        return res;
    }
}
