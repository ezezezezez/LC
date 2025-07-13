// 3606. Coupon Code Validator

import java.util.*;

public class P3606 {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<Integer> ret = new ArrayList<>();
        Set<String> validBusiness = new HashSet<>();
        validBusiness.add("electronics");
        validBusiness.add("grocery");
        validBusiness.add("pharmacy");
        validBusiness.add("restaurant");
        for (int i = 0; i < n; i++) {
            if (!isActive[i]) continue;
            if (!validBusiness.contains(businessLine[i])) continue;
            if (code[i].isEmpty()) continue;
            boolean validCode = true;
            for (int j = 0; j < code[i].length(); j++) {
                char c = code[i].charAt(j);
                if (c >= 'a' && c <= 'z') continue;
                if (c >= 'A' && c <= 'Z') continue;
                if (c >= '0' && c <= '9') continue;
                if (c != '_') {
                    validCode = false;
                    break;
                }
            }
            if (validCode) ret.add(i);
        }
        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("electronics", 1);
        orderMap.put("grocery", 2);
        orderMap.put("pharmacy", 3);
        orderMap.put("restaurant", 4);
        Collections.sort(ret, (a, b) -> {
            if (!businessLine[a].equals(businessLine[b])) {
                return Integer.compare(orderMap.get(businessLine[a]), orderMap.get(businessLine[b]));
            }
            return code[a].compareTo(code[b]);
        });
        List<String> res = new ArrayList<>();
        for (int idx : ret) res.add(code[idx]);
        return res;
    }
}
