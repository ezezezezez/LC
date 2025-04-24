import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3160. Find the Number of Distinct Colors Among the Balls
public class P3160 {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int[] ret = new int[n];
        Map<Integer, Integer> ball2Color = new HashMap<>();
        Map<Integer, Integer> color2Cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            int ball = query[0], color = query[1];
            if (ball2Color.containsKey(ball)) {
                int oldColor = ball2Color.get(ball);
                color2Cnt.merge(oldColor, -1, Integer::sum);
                if (color2Cnt.get(oldColor) == 0) color2Cnt.remove(oldColor);
                ball2Color.put(ball, color);
                color2Cnt.merge(color, 1, Integer::sum);
            } else {
                ball2Color.put(ball, color);
                color2Cnt.merge(color, 1, Integer::sum);
            }
            ret[i] = color2Cnt.size();
        }
        return ret;
    }
}
