import java.util.*;

// 3044. Most Frequent Prime
public class P3044 {
    static Set<Integer> primes = new HashSet<>(eratosthenes(1000000));

    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        Map<Integer, Integer> map = new HashMap<>();
        int maxV = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < 8; r++) {
                    int num = 0;
                    int nx = i, ny = j;
                    while (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        num = 10 * num + mat[nx][ny];
                        maxV = Math.max(maxV, num);
                        map.merge(num, 1, Integer::sum);
                        nx = nx + dx[r];
                        ny = ny + dy[r];
                    }
                }
            }
        }
        // System.out.println(map);
        int v = -1;
        int mxCnt = 0;
        for (int key : map.keySet()) {
            int count = map.get(key);
            if (key > 10 && primes.contains(key)) {
                if (count > mxCnt || (count == mxCnt && key > v)) {
                    v = key;
                    mxCnt = count;
                }
            }
        }
        return v;
    }

    static List<Integer> eratosthenes(int n) {
        int[] q = new int[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (q[i] == 1) continue;
            int j = i * 2;
            while (j <= n) {
                q[j] = 1;
                j +=i ;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (q[i] == 0)
                primes.add(i);
        }
        return primes;
    }
}
