import java.util.*;
import java.io.*;
import java.lang.*;

// 1169. Invalid Transactions

public class P1169 {
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        int nameIdx = 0, cityIdx = 0;
        Map<String, Integer> nameMap = new HashMap<>();
        Map<String, Integer> cityMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = transactions[i].split(",");
            String name = tokens[0], city = tokens[3];
            if (!nameMap.containsKey(name)) nameMap.put(name, nameIdx++);
            if (!cityMap.containsKey(city)) cityMap.put(city, cityIdx++);
        }
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] a = transactions[i].split(",");
            if (Integer.parseInt(a[2]) > 1000) {
                ret.add(transactions[i]);
                continue;
            }
            int aName = nameMap.get(a[0]), aCity = cityMap.get(a[3]);
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                String[] b = transactions[j].split(",");
                int bName = nameMap.get(b[0]), bCity = cityMap.get(b[3]);
                if (aName != bName) continue;
                if (aCity == bCity) continue;
                if (Math.abs(Integer.parseInt(a[1]) - Integer.parseInt(b[1])) <= 60) {
                    ret.add(transactions[i]);
                    break;
                }
            }
        }

        return ret;
    }
}
