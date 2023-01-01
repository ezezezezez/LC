import java.io.*;
import java.lang.*;
import java.util.*;

// 2170. Minimum Operations to Make the Array Alternating

public class P2170 {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < n; i += 2) {
            map1.merge(nums[i], 1, Integer::sum);
        }
        Set<Integer> mxCands1 = new HashSet<>();
        int mx1 = 0;
        for (int key : map1.keySet()) {
            int cnt = map1.get(key);
            if (cnt > mx1) {
                mx1 = cnt;
                mxCands1.clear();
                mxCands1.add(key);
            } else if (cnt == mx1) {
                mxCands1.add(key);
            }
        }
        for (int i = 1; i < n; i += 2) {
            map2.merge(nums[i], 1, Integer::sum);
        }
        Set<Integer> mxCands2 = new HashSet<>();
        int mx2 = 0;
        for (int key : map2.keySet()) {
            int cnt = map2.get(key);
            if (cnt > mx2) {
                mx2 = cnt;
                mxCands2.clear();
                mxCands2.add(key);
            } else if (cnt == mx2) {
                mxCands2.add(key);
            }
        }
        for (int cand1 : mxCands1) {
            for (int cand2 : mxCands2) {
                if (cand1 != cand2) {
                    return n -  map1.get(cand1) - map2.get(cand2);
                }
            }
        }
        int mxx1 = 0;
        Set<Integer> subMxCands1 = new HashSet<>();
        for (int key : map1.keySet()) {
            int cnt = map1.get(key);
            if (!mxCands1.contains(key)) {
                if (cnt > mxx1) {
                    mxx1 = cnt;
                    subMxCands1.clear();
                    subMxCands1.add(key);
                } else if (cnt == mxx1) {
                    subMxCands1.add(key);
                }
            }
        }
        int mxx2 = 0;
        Set<Integer> subMxCands2 = new HashSet<>();
        for (int key : map2.keySet()) {
            int cnt = map2.get(key);
            if (!mxCands2.contains(key)) {
                if (cnt > mxx2) {
                    mxx2 = cnt;
                    subMxCands2.clear();
                    subMxCands2.add(key);
                } else if (cnt == mxx1) {
                    subMxCands2.add(key);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int cand1 : mxCands1) {
            for (int cand2 : subMxCands2) {
                ret = Math.min(ret, n - map1.get(cand1) - map2.get(cand2));
            }
        }
        for (int cand2 : mxCands2) {
            for (int cand1 : subMxCands1) {
                ret = Math.min(ret, n -  map1.get(cand1) - map2.get(cand2));
            }
        }
        // System.out.println(ret);
        return ret == Integer.MAX_VALUE ? n / 2 : ret;
    }
}
