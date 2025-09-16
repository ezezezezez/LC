import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 673. Number of Longest Increasing Subsequence
public class P673_2 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] tempArr = nums.clone();
        Arrays.sort(tempArr);
        int p = 1;
        for (int i = 1; i < n; i++) {
            if (tempArr[i] != tempArr[i - 1]) {
                tempArr[p++] = tempArr[i];
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < p; i++) {
            map.put(tempArr[i], i + 1);
        }
        SegTree tree = new SegTree(p + 1);
        for (int v : nums) {
            int idx = map.get(v);
            int[] prevRes = tree.query(0, 0, p, 1, idx - 1);
            int len = prevRes[0], cnt = prevRes[1];
            tree.update(0, 0, p, idx, len + 1, len == 0 ? 1 : cnt);
        }

        int[] allRes = tree.query(0, 0, p, 1, p);
        int maxLen = allRes[0], maxLenCnt = allRes[1];
        return maxLenCnt;
    }

    static class SegTree {
        int[] lenArr;
        int[] cntArr;
        SegTree(int n) {
            lenArr = new int[4 * n];
            cntArr = new int[4 * n];
        }

        void update(int x, int l, int r, int idx, int len, int cnt) {
            if (idx < l || idx > r) return;
            if (l == r) {
                if (len > lenArr[x]) {
                    lenArr[x] = len;
                    cntArr[x] = cnt;
                } else if (len == lenArr[x]) {
                    cntArr[x] += cnt;
                }
                return;
            }
            int mid = l + (r - l) / 2;
            update(2 * x + 1, l, mid, idx, len, cnt);
            update(2 * x + 2, mid + 1, r, idx, len, cnt);
            if (lenArr[2 * x + 1] > lenArr[2 * x + 2]) {
                lenArr[x] = lenArr[2 * x + 1];
                cntArr[x] = cntArr[2 * x + 1];
            } else if (lenArr[2 * x + 1] < lenArr[2 * x + 2]) {
                lenArr[x] = lenArr[2 * x + 2];
                cntArr[x] = cntArr[2 * x + 2];
            } else {
                lenArr[x] = lenArr[2 * x + 1];
                cntArr[x] = cntArr[2 * x + 1] + cntArr[2 * x + 2];
            }
        }

        int[] query(int x, int l, int r, int s, int t) {
            if (t < l || s > r) return new int[2];
            if (l == r) {
                return new int[] {lenArr[x], cntArr[x]};
            }
            int mid = l + (r - l) / 2;
            int[] left = query(2 * x + 1, l, mid, s, t);
            int[] right = query(2 * x + 2, mid + 1, r, s, t);
            if (left[0] > right[0]) {
                return left;
            } else if (left[0] < right[0]) {
                return right;
            } else {
                left[1] += right[1];
                return left;
            }
        }
    }
}
