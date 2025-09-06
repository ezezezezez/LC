// 421. Maximum XOR of Two Numbers in an Array

public class P443 {
    public int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            char c = chars[i];
            while (i + 1 < n && chars[i + 1] == chars[i]) {
                i++;
            }
            sb.append(c);
            if (i - j + 1 > 1) sb.append(i - j + 1);
        }
        int ret = sb.length();
        for (int i = 0; i < ret; i++) {
            chars[i] = sb.charAt(i);
        }
        return ret;
    }
}
