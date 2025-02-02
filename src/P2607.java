import java.util.*;

public class P2607 {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        int G = gcd(n, k);
        List<Integer>[] list = new List[G];
        for (int i = 0; i < G; i++) list[i] = new ArrayList<>();
        int curG = 0;
        for (int i = 0; i < n; i++) {
            list[curG].add(arr[i]);
            curG = (curG + 1) % G;
        }
        long ret = 0;
        for (List<Integer> g : list) {
            Collections.sort(g);
            int mid = g.get(g.size() / 2);
            for (int num : g) ret += Math.abs(mid - num);
        }
        return ret;
    }

    int gcd (int a, int b) {
        return b > 0 ? gcd (b, a % b) : a;
    }
}
