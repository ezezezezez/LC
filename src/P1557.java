import java.io.*;
import java.lang.*;
import java.util.*;

// 1557. Minimum Number of Vertices to Reach All Nodes

public class P1557 {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int m = edges.size();
        int[] deg = new int[n];
        for (List<Integer> edge : edges) {
            int a = edge.get(0), b = edge.get(1);
            deg[b]++;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                ret.add(i);
            }
        }
        return ret;
    }
}
