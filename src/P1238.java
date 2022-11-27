import java.util.*;
import java.io.*;
import java.lang.*;

// 1238. Circular Permutation in Binary Representation

public class P1238 {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> temp = new ArrayList<>(1 << n);
        for (int i = 0; i < (1 << n); i++) {
            temp.add(i ^ (i >> 1));
        }
        List<Integer> ret = new ArrayList<>();
        int s = 0;
        for (int i = 0; i < temp.size(); i++) {
            if (start == temp.get(i)) {
                s = i;
                break;
            }
        }
        for (int add = 0; add < (1 << n); add++) {
            ret.add(temp.get((s + add) % (1 << n)));
        }
        return ret;
    }
}
