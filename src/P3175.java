import java.util.*;

// 3175. Find The First Player to win K Games in a Row
public class P3175 {
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        int pre = -1, cnt = 0;
        if (k >= n - 1) {
            int mx = -1, mxIdx = -1;
            for (int i = 0; i < n; i++) {
                if (skills[i] > mx) {
                    mx = skills[i];
                    mxIdx = i;
                }
            }
            return mxIdx;
        }
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int skill = skills[i];
            dq.offerLast(new int[] {skill, i});
        }
        int ret = -1;
        while (true) {
            int[] a = dq.pollFirst(), b = dq.pollFirst();
            if (a[0] > b[0]) {
                if (a[0] == pre) {
                    cnt++;
                    if (cnt == k) {
                        ret = a[1];
                        break;
                    }
                } else {
                    pre = a[0];
                    cnt = 1;
                    if (cnt == k) {
                        ret = a[1];
                        break;
                    }
                }
                dq.offerFirst(a);
                dq.offerLast(b);
            } else {
                if (b[0] == pre) {
                    cnt++;
                    if (cnt == k) {
                        ret = b[1];
                        break;
                    }
                } else {
                    pre = b[0];
                    cnt = 1;
                    if (cnt == k) {
                        ret = b[1];
                        break;
                    }
                }
                dq.offerFirst(b);
                dq.offerLast(a);
            }
        }
        return ret;
    }
}
