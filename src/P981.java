import java.util.*;
import java.io.*;
import java.lang.*;

// 981. Time Based Key-Value Store

public class P981 {
    Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    public P981() {
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        Integer k = map.get(key).floorKey(timestamp);
        if (k == null) return "";
        return map.get(key).get(k);
    }
}
