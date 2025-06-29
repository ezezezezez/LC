import java.util.*;

// 3594. Minimum Time to Transport All Individuals

public class P3594 {
    record State(double curTime, int state, int side, int stage) {
    }

    int n, k, m;
    int[] time;
    double[] mul;

    // double eps = 1e-15;
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, Integer> timeMap = new HashMap<>();

    public double minTime(int n, int k, int m, int[] time, double[] mul) {
        if (k == 1 && n > 1) return -1;
        this.n = n;
        this.k = k;
        this.m = m;
        this.time = time;
        this.mul = mul;

        for (int i = 0; i < (1 << n); i++) {
            map.put(i, enumerate(i));
            int bitCount = Integer.bitCount(i);
            if (bitCount > 0 && bitCount <= k) {
                timeMap.put(i, calcTime(i));
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Double.compare(a.curTime, b.curTime));
        pq.offer(new State(0, 0, 0, 0));
        double[][][] vis = new double[1 << n][2][m];
        for (double[][] a : vis) {
            for (double[] b : a) {
                Arrays.fill(b, Double.MAX_VALUE);
            }
        }
        vis[0][0][0] = 0;

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            double curTime = curState.curTime;
            int state = curState.state, side = curState.side, stage = curState.stage;
            if (state == (1 << n) - 1) return curTime;
            if (side == 0) {
                for (int nxtState : map.get(state)) {
                    int goState = nxtState - state;
                    double goTime = timeMap.get(goState) * mul[stage];
                    // int advance = (int) Math.floor(goTime + eps);
                    int advance = (int) Math.floor(goTime);
                    int goStage = (stage + advance) % m;
                    if (curTime + goTime >= vis[nxtState][1][goStage]) continue;
                    vis[nxtState][1][goStage] = curTime + goTime;
                    pq.offer(new State(curTime + goTime, nxtState, 1, goStage));
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (((1 << i) & state) > 0) {
                        int backState = state - (1 << i);
                        double backTime = time[i] * mul[stage];
                        // int advance = (int) Math.floor(backTime + eps);
                        int advance = (int) Math.floor(backTime);
                        int backStage = (stage + advance) % m;
                        if (curTime + backTime >= vis[backState][0][backStage]) continue;
                        vis[backState][0][backStage] = curTime + backTime;
                        pq.offer(new State(curTime + backTime, backState, 0, backStage));
                    }
                }
            }
        }
        return -1;
    }

    int calcTime(int state) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (((1 << i) & state) > 0) {
                res = Math.max(res, time[i]);
            }
        }
        return res;
    }

    Set<Integer> enumerate(int state) {
        Set<Integer> ret = new HashSet<>();
        build(0, state, k, ret);
        return ret;
    }

    void build(int idx, int state, int rem, Set<Integer> ret) {
        if (idx == n || rem == 0 || state == (1 << n) - 1) {
            return;
        }
        for (int i = idx; i < n; i++) {
            if (((1 << i) & state) == 0) {
                ret.add(state | (1 << i));
                build(i + 1, state | (1 << i), rem - 1, ret);
            }
        }
    }
}
