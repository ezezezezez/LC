import java.io.*;
import java.lang.*;
import java.util.*;

// 1471. The k Strongest Values in an Array

public class P1471 {
    public int[] getStrongest(int[] arr, int k) {
        int n = arr.length;
        int[] arr2 = arr.clone();
        Arrays.sort(arr2);
        int med = arr2[(n - 1) / 2];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int d1 = Math.abs(a - med), d2 = Math.abs(b - med);
            if (d1 != d2) return Integer.compare(d1, d2);
            return Integer.compare(a, b);
        });
        for (int num : arr) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) ret[i] = pq.poll();
        return ret;
    }

    public int[] getStrongest2(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int med = arr[(n - 1) / 2];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int d1 = Math.abs(a - med), d2 = Math.abs(b - med);
            if (d1 != d2) return Integer.compare(d1, d2);
            return Integer.compare(a, b);
        });
        for (int num : arr) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) ret[i] = pq.poll();
        return ret;
    }

    public int[] getStrongest3(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int med = arr[(n - 1) / 2];
        int[] ret = new int[k];
        for (int i = 0, j = n - 1; k > 0;) {
            if (med - arr[i] > arr[j] - med) {
                ret[--k] = arr[i++];
            } else {
                ret[--k] = arr[j--];
            }
        }
        return ret;
    }
}
