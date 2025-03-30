import java.util.Arrays;

// 2943. Maximize Area of Square Hole in Grid
public class P2943 {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int h = hBars.length, v = vBars.length;
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int mx1 = 1, cur1 = 1, mx2 = 1, cur2 = 1;
        for (int i = 1; i < h; i++) {
            if (hBars[i] - hBars[i - 1] == 1) {
                cur1++;
                mx1 = Math.max(mx1, cur1);
            } else {
                cur1 = 1;
            }
        }
        for (int i = 1; i < v; i++) {
            if (vBars[i] - vBars[i - 1] == 1) {
                cur2++;
                mx2 = Math.max(mx2, cur2);
            } else {
                cur2 = 1;
            }
        }
        int mn = Math.min(mx1, mx2);
        return (mn + 1) * (mn + 1);
    }
}
