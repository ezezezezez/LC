// 3228. Maximum Number of Operations to Move Ones to the End
public class P3228 {
    public int maxOperations(String s) {
        int n = s.length();
        int ret = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') continue;
            int j = i;
            while (j < n && s.charAt(j) == '1') j++;
            if (j == n) break;
            cnt += j - i;
            ret += cnt;
            while (j < n && s.charAt(j) == '0') {
                j++;
            }
            // System.out.println(ret);
            i = j - 1;
        }
        return ret;
    }
}
