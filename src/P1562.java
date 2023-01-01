import java.io.*;
import java.lang.*;
import java.util.*;

// 1562. Find Latest Group of Size M

public class P1562 {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        UF uf = new UF(n);
        int[] sizeArr = new int[n + 1];
        Set<Integer> set = new HashSet<>();
        int ret = -1;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            num--;
            sizeArr[1]++;
            if (num - 1 >= 0 && set.contains(num - 1)) {
                sizeArr[uf.size[uf.find(num)]]--;
                sizeArr[uf.size[uf.find(num - 1)]]--;
                uf.union(num, num - 1);
                sizeArr[uf.size[uf.find(num)]]++;
            }
            if (num + 1 < n && set.contains(num + 1)) {
                sizeArr[uf.size[uf.find(num)]]--;
                sizeArr[uf.size[uf.find(num + 1)]]--;
                uf.union(num, num + 1);
                sizeArr[uf.size[uf.find(num)]]++;
            }
            // System.out.println(Arrays.toString(sizeArr));
            if (sizeArr[m] > 0) ret = i + 1;
            set.add(num);
        }
        return ret;
    }

    static class UF {
        int[] parent;
        int[] size;

        int[] rank;
        int n;
        int setCount;

        public UF(int _n) {
            n = _n;
            setCount = _n;
            parent = new int[_n];
            size = new int[_n];
            rank = new int[_n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            //        if (rank[x] < rank[y])
            if (size[x] < size[y]) {
                int t = x;
                x = y;
                y = t;
            }
            parent[y] = x;
            //        if (rank[x] == rank[y])
            //            rank[x]++;
            size[x] += size[y];
            --setCount;
            return true;
        }

        boolean connected(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }
    }
}
