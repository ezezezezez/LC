import java.util.*;
import java.io.*;
import java.lang.*;

// 990. Satisfiability of Equality Equations

public class P990 {
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        Map<Character, Integer> map = new HashMap<>();
        int counter = 0;
        for (String equation : equations) {
            char v1 = equation.charAt(0), v2 = equation.charAt(3);
            if (!map.containsKey(v1)) map.put(v1, counter++);
            if (!map.containsKey(v2)) map.put(v2, counter++);
        }

        Arrays.sort(equations, (a, b) -> {
            String a1 = a.substring(1, 3), b1 = b.substring(1, 3);
            if (a1.equals(b1)) return 0;
            if (a1.equals("==")) return -1;
            return 1;
        });

        UF uf = new UF(counter);
        for (String equation : equations) {
            int l1 = map.get(equation.charAt(0)), l2 = map.get(equation.charAt(3));
            if (equation.substring(1, 3).equals("==")) {
                uf.union(l1, l2);
            } else if (uf.connected(l1, l2)) return false;
        }
        return true;
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
