// 3306. Count of Substrings Containing Every Vowel and K Consonants II
public class P3306 {
    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        long ret = 0;
        char[] cs = word.toCharArray();
        int A = -1, E = -1, I = -1, O = -1, U = -1;
        int[] C = new int[n + 1];
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            C[i + 1] = C[i] + (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' ? 1 : 0);
        }
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == 'a') {
                A = i;
            } else if (c == 'e') {
                E = i;
            } else if (c == 'i') {
                I = i;
            } else if (c == 'o') {
                O = i;
            } else if (c == 'u') {
                U = i;
            }
            if (A == -1 || E == -1 || I == -1 || O == -1 || U == -1 || C[i + 1] < k) continue;
            int t1 = lowerBound(C, 0, i + 2, C[i + 1] - k);
            int t2 = lowerBound(C, 0, i + 2, C[i + 1] - k + 1) - 1;
            int mn = A;
            mn = Math.min(mn, E);
            mn = Math.min(mn, I);
            mn = Math.min(mn, O);
            mn = Math.min(mn, U);
            // System.out.println(i + " " + mn + " " + t1 + " " + t2);
            if (mn >= t1 && mn <= t2) {
                ret += mn - t1 + 1;
            } else if (mn > t2) {
                ret += t2 - t1 + 1;
            }
        }
        return ret;
    }

    // first el >= val
    /**
     * @param lo - inclusive
     * @param hi - exclusive
     */
    int lowerBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
