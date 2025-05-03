// 3271. Hash Divided String
public class P3271 {
    public String stringHash(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n / k; i++) {
            String sub = s.substring(i * k, (i + 1) * k);
            int sum = 0;
            for (int j = 0; j < sub.length(); j++) {
                sum += sub.charAt(j) - 'a';
            }
            sb.append((char) (sum % 26 + 'a'));
        }
        return sb.toString();
    }
}
