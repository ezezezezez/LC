import java.util.PriorityQueue;

// 871. Minimum Number of Refueling Stops

public class P871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        int curDistance = startFuel;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int nextStation = 0, cnt = 0;
        while (curDistance < target) {
            while (nextStation < n && curDistance >= stations[nextStation][0]) {
                pq.offer(-stations[nextStation][1]);
                nextStation++;
            }
            if (pq.isEmpty()) return -1;
            curDistance += -pq.poll();
            cnt++;
        }
        return cnt;
    }
}
