// 2840. Check if Strings Can be Made Equal With Operations II
public class P2840 {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[] a = new int[26], b = new int[26];
        int[] c = new int[26], d = new int[26];
        for (int i = 0; i < n; i += 2) {
            a[s1.charAt(i) - 'a']++;
            c[s2.charAt(i) - 'a']++;
        }
        for (int i = 1; i < n; i += 2) {
            b[s1.charAt(i) - 'a']++;
            d[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != c[i] || b[i] != d[i]) return false;
        }
        return true;
    }
}
