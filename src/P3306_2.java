import java.util.HashMap;
import java.util.Map;

// 3306. Count of Substrings Containing Every Vowel and K Consonants II
public class P3306_2 {
    public long countOfSubstrings(String word, int k) {
        char[] cs = word.toCharArray();
        return count(cs, k) - count(cs, k + 1);
    }

    long count(char[] cs, int k) {
        int n = cs.length;
        long ret = 0;
        Map<Character, Integer> map = new HashMap<>();
        int t = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if ("aeiou".indexOf(c) != -1) {
                map.merge(c, 1, Integer::sum);
            } else {
                t++;
            }
            while (map.size() == 5 && t >= k) {
                char c2 = cs[j];
                if ("aeiou".indexOf(c2) != -1) {
                    map.merge(c2, -1, Integer::sum);
                    if (map.get(c2) == 0) map.remove(c2);
                } else {
                    t--;
                }
                j++;
            }
            ret += j;
        }
        return ret;
    }
}
