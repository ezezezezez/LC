// 3609. Minimum Moves to Reach Target in Grid

public class P3609_2 {
    public int minMoves(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return 0;
        if (tx < sx || ty < sy) return -1;

        if (tx == ty) {
            if (sx != 0 && sy != 0) return -1;
            if (sx == 0) {
                int t = minMoves(sx, sy, 0, ty);
                return t >= 0 ? t + 1 : -1;
            } else {
                int t = minMoves(sx, sy, tx, 0);
                return t >= 0 ? t + 1 : -1;
            }
        }

        if (tx < ty) {
            int t = sx;
            sx = sy;
            sy = t;
            t = tx;
            tx = ty;
            ty = t;
        }

        if (tx - ty < ty) {
            int t = minMoves(sx, sy, tx - ty, ty);
            return t >= 0 ? t + 1 : -1;
        } else {
            if (tx % 2 == 1) return -1;
            int t = minMoves(sx, sy, tx / 2, ty);
            return t >= 0 ? t + 1 : -1;
        }
    }
}
