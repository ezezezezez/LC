// 2673. Make Costs of Paths Equal in a Binary Tree
public class P2673_2 {
    public int minIncrements(int n, int[] cost) {
        int ret = 0;
        for (int i = n - 2; i > 0; i -= 2) {
            ret += Math.abs(cost[i] - cost[i + 1]);
            cost[i / 2] += Math.max(cost[i], cost[i + 1]);
        }
        return ret;
    }
}
