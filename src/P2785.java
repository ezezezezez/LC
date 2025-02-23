import java.util.*;

// 2785. Sort Vowels in a String
public class P2785 {
    public String sortVowels(String s) {
        int n = s.length();
        List<Integer> list = new ArrayList<>();
        List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                orig.add(i);
                list.add(i);
            } else if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                orig.add(i);
                list.add(i);
            }
        }
        Collections.sort(list, (a, b) -> Integer.compare(s.charAt(a), s.charAt(b)));
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < list.size(); i++) {
            sb.setCharAt(orig.get(i), s.charAt(list.get(i)));
        }
        return sb.toString();
    }
}
