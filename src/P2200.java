import java.io.*;
import java.lang.*;
import java.util.*;

// 2200. Find All K-Distant Indices in an Array

public class P2200 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(i - j) <= k && nums[j] == key) {
                    ret.add(i);
                    break;
                }
            }
        }
        return ret;
    }
}
