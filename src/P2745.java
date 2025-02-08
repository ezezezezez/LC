// 2745. Construct the Longest New String
public class P2745 {
    public int longestString(int x, int y, int z) {
        int mn = Math.min(x, y);
        return 2 * (2 * mn + (x == y ? 0 : 1) + z);
    }
}
