import java.io.*;
import java.lang.*;
import java.util.*;

// 1481. Least Number of Unique Integers after K Removals

public class P1481 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) map.merge(num, 1, Integer::sum);

        List<Integer> nums = new ArrayList<>(map.keySet());
        Collections.sort(nums, (a, b) -> Integer.compare(map.get(a), map.get(b)));

        int i = 0;
        while (k > 0) {
            if (map.get(nums.get(i)) <= k) {
                k -= map.get(nums.get(i));
                i++;
            } else {
                k = 0;
            }
        }

        return nums.size() - i;
    }
}
