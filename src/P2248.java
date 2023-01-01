import java.io.*;
import java.lang.*;
import java.util.*;

// 2248. Intersection of Multiple Arrays

public class P2248 {
    public List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums[0]) set.add(num);
        for (int i = 1; i < n; i++) {
            Set<Integer> set2 = new HashSet<>();
            Set<Integer> set3 = new HashSet<>();
            for (int num : nums[i]) set2.add(num);
            for (int num : set) {
                if (set2.contains(num)) set3.add(num);
            }
            set = set3;
        }
        List<Integer> ret = new ArrayList<>(set);
        Collections.sort(ret);
        return ret;
    }
}
