import java.util.*;

// 3669. Balanced K-Factor Decomposition
public class P3669_2 {
    static int MAXV = 100001;
    static List<Integer>[] facList = new List[MAXV];
    static List<Integer>[] facListMulti = new List[MAXV];
    static List<Integer>[] divisors = new List[MAXV];
    static boolean initialized = false;
    static void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(facList, i -> new ArrayList<>());
        Arrays.setAll(facListMulti, i -> new ArrayList<>());
        Arrays.setAll(divisors, i -> new ArrayList<>());

        int[] spf = new int[MAXV];
        for (int i = 2; i < MAXV; i++) {
            if (spf[i] == 0) {
                for (int j = i; j < MAXV; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }

        for (int x = 2; x < MAXV; x++) {
            int t = x;
            while (t > 1) {
                int p = spf[t];
                facListMulti[x].add(p);
                if (facList[x].isEmpty() || facList[x].get(facList[x].size() - 1) != p) {
                    facList[x].add(p);
                }
                t /= p;
            }
        }

        for (int i = 1; i < MAXV; i++) {
            for (int j = i; j < MAXV; j += i) {
                divisors[j].add(i);
            }
        }
    }

    public int[] minDifference(int n, int k) {
        init();
        this.n = n;
        this.k = k;
        if (facListMulti[n].size() <= k) {
            while (facListMulti[n].size() < k) facListMulti[n].add(1);
            ret = new int[k];
            for (int i = 0; i < k; i++) ret[i] = facListMulti[n].get(i);
            return ret;
        }
        dfs(facListMulti[n]);
        // System.out.println("counter: " + counter);
        return ret;
    }

    int n, k;
    int minDiff = Integer.MAX_VALUE;
    int[] ret;
    int counter;
    Map<String, Integer> memo = new HashMap<>();
    int dfs(List<Integer> list) {
        if (list.size() == k) {
            int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
            for (int v : list) {
                mx = Math.max(mx, v);
                mn = Math.min(mn, v);
            }
            if (mx - mn < minDiff) {
                minDiff = mx - mn;
                ret = new int[k];
                for (int i = 0; i < k; i++) ret[i] = list.get(i);
            }
            return mx - mn;
        }
        Collections.sort(list);
        String key = list.toString();
        if (memo.containsKey(key)) return memo.get(key);

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // counter++;
                List<Integer> newList = new ArrayList<>(list);
                int a = newList.get(i), b = newList.get(j);
                newList.remove(j);
                newList.remove(i);
                newList.add(a * b);
                int t = dfs(newList);
                res = Math.min(res, t);
            }
        }

        memo.put(key, res);
        return res;
    }
}
