// 3683. Earliest Time to Finish One Task

public class P3683 {
    public int earliestTime(int[][] tasks) {
        int mn = Integer.MAX_VALUE;
        for (int[] task : tasks) {
            mn = Math.min(mn, task[0] + task[1]);
        }
        return mn;
    }
}
