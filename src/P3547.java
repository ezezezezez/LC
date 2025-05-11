import java.util.*;

// 3547. Maximum Sum of Edge Values in a Graph
public class P3547 {
    public long maxScore(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, kk -> new ArrayList<>());
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
            uf.union(a, b);
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = uf.find(i);
            map.computeIfAbsent(parent, kk -> new HashSet<>()).add(i);
        }
        List<Set<Integer>> cycles = new ArrayList<>();
        List<Set<Integer>> lines = new ArrayList<>();
        int cycleSize = 0;
        for (Set<Integer> set : map.values()) {
            boolean flag = true;
            for (int x : set) {
                if (g[x].size() != 2) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cycles.add(set);
                cycleSize += set.size();
            } else {
                lines.add(set);
            }
        }
        Collections.sort(cycles, (a, b) -> Integer.compare(b.size(), a.size()));
        Collections.sort(lines, (a, b) -> Integer.compare(b.size(), a.size()));
        // System.out.println(cycles);
        // System.out.println(lines);
        return calc(cycles, n, g, true) + calc(lines, n - cycleSize, g, false);
    }

    long calc(List<Set<Integer>> data, int cur, List<Integer>[] g, boolean isCycle) {
        // System.out.println("cur :" + cur);
        long ret = 0;
        for (Set<Integer> shape : data) {
            // System.out.println("cur: " + cur);
            if (!isCycle && shape.size() == 1) continue;
            if (!isCycle && shape.size() == 2) {
                ret += (long) cur * (cur - 1);
                // System.out.println(ret);
                cur -= 2;
                continue;
            }
            Deque<int[]> dq = new ArrayDeque<>();
            int first = shape.iterator().next(), second = -1;
            if (!isCycle) {
                int[] pair = findBegin(shape, g);
                first = pair[0];
                second = pair[1];
            }
            Set<Integer> seen = new HashSet<>();
            seen.add(first);
            dq.offer(new int[] {first, cur});
            if (!isCycle) {
                dq.offer(new int[] {second, cur - 1});
                ret += (long) cur * (cur - 1);
                seen.add(second);
                cur--;
            }
            while (seen.size() < shape.size()) {
                int[] pair = dq.poll();
                int node = pair[0], val = pair[1];
                for (int nxt : g[node]) {
                    if (!seen.contains(nxt)) {
                        cur--;
                        ret += (long) val * cur;
                        dq.offer(new int[] {nxt, cur});
                        seen.add(nxt);
                    }
                }
            }
            // System.out.println(cur);
            ret += isCycle ? (long) cur * (cur + 1) : 0;
            cur -= 1;
            // System.out.println(ret);
            // System.out.println();
        }
        return ret;
    }

    int[] findBegin(Set<Integer> line, List<Integer>[] g) {
        int begin = -1;
        for (int x : line) {
            if (g[x].size() == 1) {
                begin = x;
                break;
            }
        }
        List<Integer> path = new ArrayList<>();
        dfs(begin, -1, g, path);
        // System.out.println(path);
        return new int[] {path.get(path.size() / 2), path.get(path.size() / 2 - 1)};
    }

    void dfs(int cur, int pre, List<Integer>[] g, List<Integer> path) {
        path.add(cur);
        for (int nxt : g[cur]) {
            if (nxt == pre) continue;
            dfs(nxt, cur, g, path);
        }
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
