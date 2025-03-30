// 2938. Separate Black and White Balls
public class P2938 {
    public long minimumSteps(String s) {
        int n = s.length();
        int j = n - 1;
        long ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '1') {
                ret += j - i;
                j--;
            }
        }
        return ret;
    }
}
