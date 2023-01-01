import java.io.*;
import java.lang.*;
import java.util.*;

// 2348. Number of Zero-Filled Subarrays

public class P2349 {
    Map<Integer, TreeSet<Integer>> map = new HashMap<>();
    Map<Integer, Integer> arr = new HashMap<>();
    public P2349() {

    }

    public void change(int index, int number) {
        if (arr.containsKey(index)) {
            int ori = arr.get(index);
            map.get(ori).remove(index);
            if (map.get(ori).isEmpty()) map.remove(ori);
        }
        arr.put(index, number);
        map.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        if (!map.containsKey(number)) return -1;
        return map.get(number).first();
    }
}
