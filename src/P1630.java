import java.io.*;
import java.lang.*;
import java.util.*;

// 1630. Arithmetic Subarrays

public class P1630 {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = nums.length, m = l.length;
        List<Boolean> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int left = l[i], right = r[i];
            TreeMap<Integer, Integer> ts = new TreeMap<>();
            for (int j = left; j <= right; j++) {
                ts.merge(nums[j], 1, Integer::sum);
            }
            List<Integer> list = new ArrayList<>();
            for (int key : ts.keySet()) {
                int cnt = ts.get(key);
                for (int j = 0; j < cnt; j++) {
                    list.add(key);
                }
            }
            int diff = list.get(1) - list.get(0);
            boolean flag = true;
            for (int j = 2; j < list.size(); j++) {
                if (list.get(j) - list.get(j - 1) != diff) {
                    flag = false;
                    break;
                }
            }
            ret.add(flag);
        }
        return ret;
    }

    public List<Boolean> checkArithmeticSubarrays2(int[] nums, int[] l, int[] r) {
        int n = nums.length, m = l.length;
        List<Boolean> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = l[i]; j <= r[i]; j++) {
                list.add(nums[j]);
            }
            Collections.sort(list);
            int diff = list.get(1) - list.get(0);
            boolean flag = true;
            for (int j = 2; j < list.size(); j++) {
                if (list.get(j) - list.get(j - 1) != diff) {
                    flag = false;
                    break;
                }
            }
            ret.add(flag);
        }
        return ret;
    }

    public List<Boolean> checkArithmeticSubarrays3(int[] nums, int[] l, int[] r) {
        int n = nums.length, m = l.length;
        List<Boolean> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] list = new int[r[i] - l[i] + 1];
            for (int j = l[i]; j <= r[i]; j++) {
                list[j - l[i]] = nums[j];
            }
            Arrays.sort(list);
            int diff = list[1] - list[0];
            boolean flag = true;
            for (int j = 2; j < list.length; j++) {
                if (list[j] - list[j - 1] != diff) {
                    flag = false;
                    break;
                }
            }
            ret.add(flag);
        }
        return ret;
    }
}
