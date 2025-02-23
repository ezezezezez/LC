import java.util.HashMap;
import java.util.Map;

// 2800. Shortest String That Contains Three Strings
public class P2800 {
    public String minimumString(String a, String b, String c) {
        String r1 = findCommon(findCommon(a, b), c);
        System.out.println(r1);
        String r2 = findCommon(findCommon(b, a), c);
        System.out.println(r2);
        String r3 = findCommon(findCommon(a, c), b);
        String r4 = findCommon(findCommon(c, a), b);
        String r5 = findCommon(findCommon(b, c), a);
        String r6 = findCommon(findCommon(c, b), a);
        String r7 = findCommon(c, findCommon(a, b));
        String r8 = findCommon(c, findCommon(b, a));
        String r9 = findCommon(b, findCommon(a, c));
        String r10 = findCommon(b, findCommon(c, a));
        String r11 = findCommon(a, findCommon(b, c));
        String r12 = findCommon(a, findCommon(c, b));
        String ret = r1;
        ret = getSmaller(ret, r2);
        ret = getSmaller(ret, r3);
        ret = getSmaller(ret, r4);
        ret = getSmaller(ret, r5);
        ret = getSmaller(ret, r6);
        ret = getSmaller(ret, r7);
        ret = getSmaller(ret, r8);
        ret = getSmaller(ret, r9);
        ret = getSmaller(ret, r10);
        ret = getSmaller(ret, r11);
        ret = getSmaller(ret, r12);
        return ret;
    }

    String getSmaller(String a, String b) {
        if (a.length() < b.length()) return a;
        else if (a.length() > b.length()) return b;
        return a.compareTo(b) < 0 ? a : b;
    }

    String findCommon(String a, String b) {
        if (a.contains(b)) return a;
        if (b.contains(a)) return b;
        for (int i = 0; i < a.length(); i++) {
            int j = 0;
            for (; j < b.length() && i + j < a.length(); j++) {
                if (a.charAt(i + j) != b.charAt(j)) {
                    break;
                }
            }
            if (i + j == a.length()) {
                return a + b.substring(j);
            }
        }
        return a + b;
    }
}
