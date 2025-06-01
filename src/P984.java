// 984. String Without AAA or BBB

public class P984 {
    public String strWithout3a3b(int a, int b) {
        StringBuilder ret = new StringBuilder();
        if (a == b) {
            for (int i = 0; i < a + b; i += 2) {
                ret.append("ab");
            }
            return ret.toString();
        }
        if (a > b) {
            while (a > 0) {
                if (a == 1) {
                    ret.append('a');
                    a--;
                } else {
                    ret.append("aa");
                    a -= 2;
                }
                if (b > 0) {
                    ret.append('b');
                    b--;
                }
            }
            int cur = 0;
            while (b > 0) {
                ret.insert(cur, 'b');
                b--;
                cur += 2;
                if (ret.charAt(cur) == 'b') cur += 2;
            }
        } else {
            while (b > 0) {
                if (b == 1) {
                    ret.append('b');
                    b--;
                } else {
                    ret.append("bb");
                    b -= 2;
                }
                if (a > 0) {
                    ret.append('a');
                    a--;
                }
            }
            int cur = 0;
            while (a > 0) {
                ret.insert(cur, 'a');
                a--;
                cur += 2;
                if (ret.charAt(cur) == 'a') cur += 2;
            }
        }
        return ret.toString();
    }
}
