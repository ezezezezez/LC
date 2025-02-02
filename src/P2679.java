import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 2679. Sum in a Matrix
public class P2679 {
    public int matrixSum(int[][] nums) {
        List<PriorityQueue<Integer>> list = new ArrayList<>();
        int m = nums.length, n = nums[0].length;
        for (int[] num : nums) {
            list.add(new PriorityQueue<>((a, b) -> Integer.compare(b, a)));
            for (int v : num) {
                list.get(list.size() - 1).offer(v);
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int mx = 0;
            for (int j = 0; j < m; j++) {
                mx = Math.max(mx, list.get(j).poll());
            }
            ret += mx;
        }
        return ret;
    }
}
