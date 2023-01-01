import java.io.*;
import java.lang.*;
import java.util.*;

// 2191. Sort the Jumbled Numbers

public class P2191 {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[] ds = new int[n];
        int[] digits = new int[15];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int idx = num == 0 ? 1 : 0;
            if (num == 0) digits[0] = 0;
            while (num > 0) {
                digits[idx++] = num % 10;
                num /= 10;
            }
            int s = 0;
            for (int j = idx - 1; j >= 0; j--) {
                s = 10 * s + mapping[digits[j]];
            }
            ds[i] = s;
        }
        // System.out.println(Arrays.toString(ds));
        Integer[] idxMap = new Integer[n];
        Arrays.setAll(idxMap, k -> k);
        Arrays.sort(idxMap, (a, b) -> Integer.compare(ds[a], ds[b]));
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[idxMap[i]];
        }
        return ret;
    }

    public int[] sortJumbled2(int[] mapping, int[] nums) {
        int n = nums.length;
        int[] ds = new int[n];
        int[] digits = new int[15];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int idx = 0;
            while (num > 0) {
                digits[idx++] = num % 10;
                num /= 10;
            }
            int s = 0;
            for (int j = idx - 1; j >= 0; j--) {
                s = 10 * s + mapping[digits[j]];
            }
            ds[i] = nums[i] == 0 ? mapping[0] : s;
        }
        // System.out.println(Arrays.toString(ds));
        Integer[] idxMap = new Integer[n];
        Arrays.setAll(idxMap, k -> k);
        Arrays.sort(idxMap, (a, b) -> Integer.compare(ds[a], ds[b]));
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[idxMap[i]];
        }
        return ret;
    }
}
