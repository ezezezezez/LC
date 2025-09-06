import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3661. Maximum Walls Destroyed by Robots
public class P3661 {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length, m = walls.length;
        Arrays.sort(walls);
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Integer.compare(robots[a], robots[b]));
        List<Integer>[] gapList = new List[n + 1];
        Arrays.setAll(gapList, kk -> new ArrayList<>());
        int j = 0;
        while (j < m && walls[j] < robots[idx[0]]) {
            gapList[0].add(walls[j]);
            j++;
        }
        for (int i = 1; i < n; i++) {
            while (j < m && walls[j] < robots[idx[i]]) {
                if (walls[j] != robots[idx[i - 1]]) gapList[i].add(walls[j]);
                j++;
            }
        }
        while (j < m) {
            if (walls[j] != robots[idx[n - 1]]) gapList[n].add(walls[j]);
            j++;
        }
        int[][] gaps = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            int[] gap = new int[gapList[i].size()];
            for (int p = 0; p < gapList[i].size(); p++) {
                gap[p] = gapList[i].get(p);
            }
            gaps[i] = gap;
        }
        int[] dpLeft = new int[n], dpRight = new int[n];
        dpLeft[0] = gaps[0].length - lowerBound(gaps[0], 0, gaps[0].length, robots[idx[0]] - distance[idx[0]]);
        j = 0;
        List<Integer> wallAtRobots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (j < m && walls[j] < robots[idx[i]]) {
                j++;
            }
            if (j < m && walls[j] == robots[idx[i]]) {
                wallAtRobots.add(walls[j]);
            }
        }
        dpRight[0] = upperBound(gaps[1], 0, gaps[1].length, robots[idx[0]] + distance[idx[0]]);

        for (int i = 1; i < n; i++) {
            int rightReach = robots[idx[i - 1]] + distance[idx[i - 1]];
            int leftReach = robots[idx[i]] - distance[idx[i]];
            int[] gap = gaps[i];
            int A = upperBound(gap, 0, gap.length, rightReach);
            int B = gap.length - lowerBound(gap, 0, gap.length, leftReach);
            int C = rightReach >= leftReach ? A - lowerBound(gap, 0, gap.length, leftReach) : 0;
            // System.out.println(i + " " + A + " " + B + " " + C);
            dpLeft[i] = Math.max(dpLeft[i - 1] + B, dpRight[i - 1] + B - C);
            int[] nxtGap = gaps[i + 1];
            rightReach = robots[idx[i]] + distance[idx[i]];
            int D = upperBound(nxtGap, 0, nxtGap.length, rightReach);
            dpRight[i] = Math.max(dpLeft[i - 1] + D, dpRight[i - 1] + D);
        }

        // System.out.println(Arrays.toString(gapList));
        // System.out.println(Arrays.toString(idx));
        // System.out.println(wallAtRobots);
        // System.out.println(Arrays.toString(walls));
        // System.out.println(Arrays.toString(dpLeft));
        // System.out.println(Arrays.toString(dpRight));
        return Math.max(dpLeft[n - 1], dpRight[n - 1]) + wallAtRobots.size();
    }

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

    int upperBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] > val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
