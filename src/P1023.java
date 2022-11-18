import java.util.*;
import java.io.*;
import java.lang.*;

// 1023. Camelcase Matching

public class P1023 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ret = new ArrayList<>();
        for (String query : queries) {
            boolean flag = true;
            int j = 0;
            for (int i = 0; i < query.length(); i++) {
                if (j < pattern.length() && query.charAt(i) == pattern.charAt(j)) j++;
                else if (Character.isUpperCase(query.charAt(i))) {
                    flag = false;
                    ret.add(false);
                    break;
                }
            }
            if (flag) {
                ret.add(j == pattern.length());
            }
        }

        return ret;
    }
}
