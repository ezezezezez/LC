import java.util.*;
import java.io.*;
import java.lang.*;

// 1146. Snapshot Array

public class P1146 {
    List<int[]>[] arr;
    int curSnap;

//    public SnapshotArray(int length) {
    public P1146(int length) {
        arr = new List[length];
        Arrays.setAll(arr, k -> new ArrayList<>());
    }

    public void set(int index, int val) {
        if (arr[index].isEmpty() || arr[index].get(arr[index].size() - 1)[1] < curSnap) {
            arr[index].add(new int[]{val, curSnap});
        } else {
            arr[index].get(arr[index].size() - 1)[0] = val;
        }
    }

    public int snap() {
        return curSnap++;
    }

    public int get(int index, int snapId) {
        List<int[]> list = arr[index];
        if (list.isEmpty()) return 0;
        int lo = 0, hi = list.size() - 1;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (list.get(mid)[1] <= snapId) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t == -1 ? 0 : list.get(t)[0];
    }
}
