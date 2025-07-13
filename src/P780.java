// 780. Reaching Points

public class P780 {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            if (tx >= ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx < sx || ty < sy) return false;

        return sx == tx ? (ty - sy) % sx == 0 : (tx - sx) % sy == 0;
    }
}
