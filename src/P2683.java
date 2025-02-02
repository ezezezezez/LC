// 2683. Neighboring Bitwise XOR
public class P2683 {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int v = derived[i];
            cur = v ^ cur;
            if (i == n - 1 && cur != 0) return false;
        }
        cur = 1;
        for (int i = 0; i < n; i++) {
            int v = derived[i];
            cur = v ^ cur;
            if (i == n - 1 && cur != 1) return false;
        }
        return true;
    }
}
