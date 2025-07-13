// 3609. Minimum Moves to Reach Target in Grid

public class P3609 {
    public int minMoves(int sx, int sy, int tx, int ty) {
        return check(sx, sy, tx, ty);
    }

    int check(int sx, int sy, int tx, int ty) {
        int move = 0;
        while (tx > sx && ty > sy) {
            if (tx == ty) {
                move++;
                if (sx == 0) {
                    while (ty > sy) {
                        if (ty % 2 == 1) return -1;
                        ty /= 2;
                        move++;
                    }
                    return ty == sy ? move : -1;
                } else if (sy == 0) {
                    while (tx > sx) {
                        if (tx % 2 == 1) return -1;
                        tx /= 2;
                        move++;
                    }
                    return tx == sx ? move : -1;
                } else {
                    return -1;
                }
            } else if (tx > ty) {
                if (tx - ty > ty) {
                    if (tx % 2 == 1) return -1;
                    tx /= 2;
                } else {
                    tx -= ty;
                }
            } else {
                if (ty - tx > tx) {
                    if (ty % 2 == 1) return -1;
                    ty /= 2;
                } else {
                    ty -= tx;
                }
            }
            move++;
        }
        if (tx < sx || ty < sy) return -1;
        if (sx == tx && sy == ty) return move;

        if (sx == tx) {
            while (sy < ty) {
                if (tx > ty) {
                    return -1;
                } else {
                    if (ty - tx > tx) {
                        if (ty % 2 == 1) return -1;
                        ty /= 2;
                    } else {
                        ty -= tx;
                    }
                }
                // System.out.println(tx + " " + ty);
                move++;
            }
            return sy == ty ? move : -1;
        }

        if (sy == ty) {
            while (sx < tx) {
                if (ty > tx) {
                    return -1;
                } else {
                    if (tx - ty > ty) {
                        if (tx % 2 == 1) return -1;
                        tx /= 2;
                    } else {
                        tx -= ty;
                    }
                }
                move++;
            }
            return sx == tx ? move : -1;
        }

        return -1;
    }
}
