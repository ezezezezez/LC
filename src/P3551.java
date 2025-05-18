import java.util.*;

// 3551. Minimum Swaps to Sort by Digit Sum
public class P3551 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        int[][] arr2 = new int[n][3];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int num = nums[i];
            int t = num;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            arr[i][0] = sum;
            arr[i][1] = num;
            arr[i][2] = i;
            arr2[i][0] = sum;
            arr2[i][1] = num;
            arr2[i][2] = i;
            map.put(num, i);
        }
        Arrays.sort(arr2, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        // for (int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(arr2[i]));
        // }
        // System.out.println();
        // for (int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(arr[i]));
        // }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i][2] != arr2[i][2]) {
                cnt++;
                int[] t = arr[i];
                int idx = map.get(arr2[i][1]);
                arr[i] = arr[idx];
                arr[idx] = t;
                map.put(t[1], idx);
                map.put(arr[i][1], i);
            }
            // System.out.println("---");
            // for (int j = 0; j < n; j++) {
            //     System.out.println(Arrays.toString(arr[j]));
            // }
        }
        return cnt;
    }
}
