import java.io.*;
import java.lang.*;
import java.util.*;

// 2215. Find the Difference of Two Arrays

public class P2215 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int num : nums1) {
            if (!set2.contains(num)) s1.add(num);
        }
        for (int num : nums2) {
            if (!set1.contains(num)) s2.add(num);
        }
        ret.add(new ArrayList<>(s1));
        ret.add(new ArrayList<>(s2));
        return ret;
    }
}
