import java.io.*;
import java.lang.*;
import java.util.*;

// 1487. Making File Names Unique

public class P1487 {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for (String name : names) {
            if (map.containsKey(name)) {
                for (int k = map.get(name) + 1; ; k++) {
                    String newName = name + "(" + k + ")";
                    if (!map.containsKey(newName)) {
                        res.add(newName);
                        map.put(name, k);
                        map.put(newName, 0);
                        break;
                    }
                }
            } else {
                res.add(name);
                map.put(name, 0);
            }
        }

        String[] ret = new String[res.size()];
        for (int i = 0; i < ret.length; i++) ret[i] = res.get(i);
        return ret;
    }
}
