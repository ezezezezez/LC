import java.util.*;

// 3267. Count Almost Equal Pairs II
public class P3267 {
    public int countPairs(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; i++) {
            swap(nums, i, n - 1 - i);
        }
        int ret = 0;
        int[] d = new int[7];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        for (int num : nums) {
            map.merge(num, -1, Integer::sum);
            if (map.get(num) == 0) map.remove(num);
            // System.out.println(map);
            int idx = 0;
            int t = num;
            while (t > 0) {
                d[idx++] = t % 10;
                t /= 10;
            }
            if (map.containsKey(num)) ret += map.get(num);
            Set<Integer> seen1 = new HashSet<>();
            for (int i = 0; i < idx; i++) {
                for (int j = i + 1; j < idx; j++) {
                    if (d[i] != d[j]) {
                        swap(d, i, j);
                        int v = getNum(d, idx);
                        if (seen1.contains(v)) {
                            swap(d, i, j);
                            continue;
                        }
                        ret += map.getOrDefault(v, 0);
                        seen1.add(v);
                        swap(d, i, j);
                    }
                }
            }
            // System.out.println("1: " + num + " " + ret);

            Set<Integer> seen2 = new HashSet<>();
            for (int i = 0; i < idx; i++) {
                for (int j = i + 1; j < idx; j++) {
                    for (int k = j + 1; k < idx; k++) {
                        if (d[i] != d[j] && d[i] != d[k] && d[j] != d[k]) {
                            for (int v : swap3(d, i, j, k, idx)) {
                                if (seen2.contains(v)) continue;
                                ret += map.getOrDefault(v, 0);
                                seen2.add(v);
                            }
                        }
                    }
                }
            }
            // System.out.println("2: " + num + " " + ret);

            Set<Integer> seen3 = new HashSet<>();
            for (int i = 0; i < idx; i++) {
                for (int j = i + 1; j < idx; j++) {
                    for (int k = j + 1; k < idx; k++) {
                        for (int l = k + 1; l < idx; l++) {
                            for (int v : swap4(d, i, j, k, l, idx)) {
                                if (v == num || seen1.contains(v) || seen2.contains(v) || seen3.contains(v)) continue;
                                // if (map.containsKey(v)) System.out.println(num + " " + v);
                                ret += map.getOrDefault(v, 0);
                                seen3.add(v);
                            }
                        }
                    }
                }
            }
            // System.out.println("3: " + num + " " + ret);
            // System.out.println();
        }
        return ret;
    }

    void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    List<Integer> swap3(int[] arr, int i, int j, int k, int idx) {
        List<Integer> ret = new ArrayList<>();
        int[] arr1 = arr.clone();
        int t = arr1[i];
        arr1[i] = arr1[j];
        arr1[j] = t;
        t = arr1[j];
        arr1[j] = arr1[k];
        arr1[k] = t;
        ret.add(getNum(arr1, idx));

        int[] arr2 = arr.clone();
        t = arr2[i];
        arr2[i] = arr2[k];
        arr2[k] = t;
        t = arr2[j];
        arr2[j] = arr2[k];
        arr2[k] = t;
        ret.add(getNum(arr2, idx));

        return ret;
    }

    List<Integer> swap4(int[] arr, int i, int j, int k, int l, int idx) {
        List<Integer> ret = new ArrayList<>();
        int[] arr1 = arr.clone();
        // i <-> j, k <-> l
        int t = arr1[i];
        arr1[i] = arr1[j];
        arr1[j] = t;
        t = arr1[k];
        arr1[k] = arr1[l];
        arr1[l] = t;
        ret.add(getNum(arr1, idx));

        int[] arr4 = arr.clone();
        // i <-> k, j <-> l
        t = arr4[i];
        arr4[i] = arr4[k];
        arr4[k] = t;
        t = arr4[j];
        arr4[j] = arr4[l];
        arr4[l] = t;
        ret.add(getNum(arr4, idx));

        int[] arr7 = arr.clone();
        // i <-> l, j <-> k
        t = arr7[i];
        arr7[i] = arr7[l];
        arr7[l] = t;
        t = arr7[j];
        arr7[j] = arr7[k];
        arr7[k] = t;
        ret.add(getNum(arr7, idx));

        return ret;
    }

    int getNum(int[] arr, int idx) {
        int ret = 0;
        for (int i = idx - 1; i >= 0; i--) {
            ret = ret * 10 + arr[i];
        }
        return ret;
    }
}
