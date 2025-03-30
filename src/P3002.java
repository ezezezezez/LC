import java.util.HashMap;
import java.util.Map;

// 3002. Maximum Size of a Set After Removals
public class P3002 {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : nums1) map1.merge(num, 1, Integer::sum);
        for (int num : nums2) map2.merge(num, 1, Integer::sum);
        int t1 = n / 2, t2 = n / 2;
        for (int value : map1.values()) {
            t1 -= value - 1;
            if (t1 <= 0) break;
        }
        for (int value : map2.values()) {
            t2 -= value - 1;
            if (t2 <= 0) break;
        }
        Map<Integer, Integer> map3 = new HashMap<>();
        for (int key : map1.keySet()) {
            map3.merge(key, 1, Integer::sum);
        }
        for (int key : map2.keySet()) {
            map3.merge(key, 1, Integer::sum);
        }
        int rem = Math.max(0, t1) + Math.max(0, t2);
        for (int value : map3.values()) {
            if (value == 2 && rem > 0) {
                rem--;
            }
        }
        return map3.size() - rem;
    }
}
