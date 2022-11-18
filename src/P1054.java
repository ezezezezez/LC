import java.util.*;
import java.io.*;
import java.lang.*;

// 1054. Distant Barcodes

public class P1054 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int barcode : barcodes) map.merge(barcode, 1, Integer::sum);

        List<Integer> list = new ArrayList<>(map.keySet());
        // System.out.println(list);
        int m = list.size();
        Integer[] ids = new Integer[m];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> Integer.compare(map.get(list.get(b)), map.get(list.get(a))));

        int[] ret = new int[n];

        int pos = 0;
        for (int i = 0, j = 0; i < n; i++, j = j + 2 >= n ? 1 : j + 2) {
            ret[j] = list.get(ids[pos]);
            if (map.merge(list.get(ids[pos]), -1, Integer::sum) == 0) pos++;
        }

        return ret;
    }
}
