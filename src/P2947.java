import java.util.ArrayList;
import java.util.Arrays;

// 2947. Count Beautiful Substrings I
public class P2947 {
    public int beautifulSubstrings(String s, int k) {
        int n = s.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int vowel = 0, consonant = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (isVowel(c)) vowel++;
                else consonant++;
                if (vowel == consonant && vowel * consonant % k == 0) ret++;
            }
        }
        return ret;
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
