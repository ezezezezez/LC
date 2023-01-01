import java.io.*;
import java.lang.*;
import java.util.*;

// 2502. Design Memory Allocator

public class P2502 {
    boolean[] mem;
    Map<Integer, List<int[]>> map = new HashMap<>();
    int n;

    public P2502(int n) {
        this.n = n;
        mem = new boolean[n];
    }

    public int allocate(int size, int mID) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (mem[i]) {
                cnt = 0;
            } else {
                cnt++;
            }
            if (cnt == size) {
                map.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{i - size + 1, size});
                for (int j = i - size + 1; j <= i; j++) mem[j] = true;
                return i - size + 1;
            }
        }
        return -1;
    }

    public int free(int mID) {
        if (!map.containsKey(mID)) return 0;
        int ret = 0;
        for (int[] node : map.get(mID)) {
            int idx = node[0], size = node[1];
            for (int j = idx; j < idx + size; j++) {
                mem[j] = false;
            }
            ret += size;
        }
        map.remove(mID);
        return ret;
    }
}
