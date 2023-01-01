import java.io.*;
import java.lang.*;
import java.util.*;

// 1620. Coordinate With Maximum Network Quality

public class P1620 {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int n = towers.length;
        int[] ret = new int[2];
        double mxQuality = 0;
        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                double quality = 0;
                for (int[] tower : towers) {
                    int x = tower[0], y = tower[1], q = tower[2];
                    int dx = x - i, dy = y - j;
                    if (dx * dx + dy * dy <= radius * radius) {
                        quality += Math.floor(q / (1 + Math.sqrt(dx * dx + dy * dy)));
                    }
                }
                if (quality > mxQuality) {
                    // System.out.println(i + " " + j + " " + quality);
                    mxQuality = quality;
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }
        return ret;
    }
}
