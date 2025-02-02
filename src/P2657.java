import java.util.HashSet;
import java.util.Set;

// 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
public class P2657 {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ret = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(A[i]);
            int cnt = 0;
            for (int j = 0; j <= i; j++) {
                if (set.contains(B[j])) cnt++;
            }
            ret[i] = cnt;
        }
        return ret;
    }
}
