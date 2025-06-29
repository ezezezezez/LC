// 3588. Find Maximum Area of a Triangle

import java.util.HashMap;
import java.util.Map;

public class P3588 {
    public long maxArea(int[][] coords) {
        int n = coords.length;
        long ret = 0;

        int top = 0, bottom = Integer.MAX_VALUE, left = Integer.MAX_VALUE, right = 0;
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        Map<Integer, Integer> topMap = new HashMap<>();
        Map<Integer, Integer> bottomMap = new HashMap<>();
        for (int[] coord : coords) {
            int x = coord[0], y = coord[1];
            top = Math.max(top, y);
            bottom = Math.min(bottom, y);
            left = Math.min(left, x);
            right = Math.max(right, x);

            leftMap.merge(y, x, Math::min);
            rightMap.merge(y, x, Math::max);
            topMap.merge(x, y, Math::max);
            bottomMap.merge(x, y, Math::min);
        }

        for (int key : leftMap.keySet()) {
            int leftPoint = leftMap.get(key), rightPoint = rightMap.get(key);
            int len = rightPoint - leftPoint;
            ret = Math.max(ret, 1L * len * Math.max(top - key, key - bottom));
        }

        for (int key : topMap.keySet()) {
            int topPoint = topMap.get(key), bottomPoint = bottomMap.get(key);
            int len = topPoint - bottomPoint;
            ret = Math.max(ret, 1L * len * Math.max(right - key, key - left));
        }

        return ret == 0 ? -1 : ret;
    }
}
