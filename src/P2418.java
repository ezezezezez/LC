import java.io.*;
import java.lang.*;
import java.util.*;

// 2418. Sort the People

public class P2418 {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> Integer.compare(heights[b], heights[a]));
        String[] ret = new String[n];
        for (int i = 0; i < n; i++) ret[i] = names[ids[i]];
        return ret;
    }
}
