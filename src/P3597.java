import java.util.*;

// 3597. Partition String

public class P3597 {
    public List<String> partitionString(String s) {
        Set<String> set = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return new ArrayList<>(set);
    }
}
