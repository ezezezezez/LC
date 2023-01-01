import java.io.*;
import java.lang.*;
import java.util.*;

// 2443. Sum of Number and Its Reverse

public class P2452 {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n = queries.length;
        int m = queries[0].length();
        List<String> ret = new ArrayList<>();
        for (String query : queries) {
            for(String word : dictionary) {
                int diff = 0;
                for (int i = 0; i < m; i++) {
                    if (query.charAt(i) != word.charAt(i)) diff++;
                }
                if (diff <= 2) {
                    ret.add(query);
                    break;
                }
            }
        }
        return ret;
    }
}
