// 3522. Calculate Score After Performing Instructions
public class P3522 {
    public long calculateScore(String[] instructions, int[] values) {
        int n = values.length;
        long ret = 0;
        int idx = 0;
        boolean[] vis = new boolean[n];
        while (true) {
            String ins = instructions[idx];
            int v = values[idx];
            vis[idx] = true;
            if ("add".equals(ins)) {
                ret += v;
                idx++;
            } else {
                idx = idx + v;
            }
            if (idx < 0 || idx >= n || vis[idx]) break;
        }
        return ret;
    }
}
