import java.util.*;
import java.io.*;
import java.lang.*;

// 937. Reorder Data in Log Files

public class P937 {
    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, k -> k);

        Arrays.sort(ids, (a, b) -> {
            String loga = logs[a], logb = logs[b];
            String[] a1 = loga.split(" ");
            String[] b1 = logb.split(" ");
            String typea = Character.isDigit(a1[1].charAt(0)) ? "dig" : "let";
            String typeb = Character.isDigit(b1[1].charAt(0)) ? "dig" : "let";
            if (typea.equals("let") && typeb.equals("dig")) return -1;
            if (typea.equals("dig") && typeb.equals("let")) return 1;

            if (typea.equals("let")) {
                StringBuilder contenta = new StringBuilder();
                StringBuilder contentb = new StringBuilder();
                for (int i = 1; i < a1.length; i++) contenta.append(a1[i]).append(' ');
                for (int i = 1; i < b1.length; i++) contentb.append(b1[i]).append(' ');
                int cmp = contenta.toString().compareTo(contentb.toString());
                if (cmp != 0) return cmp;
                return a1[0].compareTo(b1[0]);
            }
            return Integer.compare(a, b);
        });

        String[] ret = new String[n];
        for (int i = 0; i < n; i++) ret[i] = logs[ids[i]];

        return ret;
    }
}
