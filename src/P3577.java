// 3577. Count the Number of Computer Unlocking Permutations
public class P3577 {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long ret = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0 && complexity[i] <= complexity[0]) return 0;
        }
        int j = n - 1;
        int mod = 1000000007;
        for (int i = 1; i < n; i++) {
            ret = ret * j % mod;
            j--;
        }

        return (int) ret;
    }
}
