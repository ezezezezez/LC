import java.util.*;

// 3531. Count Covered Buildings
public class P3531 {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int ret = 0;
        int[] left = new int[n], right = new int[n], top = new int[n], bottom = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Arrays.fill(top, -1);
        Arrays.fill(bottom, -1);
        for (int[] building : buildings) {
            int x = building[0] - 1, y = building[1] - 1;
            if (left[x] == -1 || left[x] > y) left[x] = y;
            if (right[x] == -1 || right[x] < y) right[x] = y;
            if (top[y] == -1 || top[y] > x) top[y] = x;
            if (bottom[y] == -1 || bottom[y] < x) bottom[y] = x;
        }
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        // System.out.println(Arrays.toString(top));
        // System.out.println(Arrays.toString(bottom));
        for (int[] building : buildings) {
            int x = building[0] - 1, y = building[1] - 1;
            if (left[x] != -1 && right[x] != -1 && top[y] != -1 && bottom[y] != -1 && left[x] < y && right[x] > y && top[y] < x && bottom[y] > x) ret++;
        }
        return ret;
    }
}
