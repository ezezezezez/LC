import java.io.*;
import java.lang.*;
import java.util.*;

// 2211. Count Collisions on a Road

public class P2211 {
    public int countCollisions(String directions) {
        int n = directions.length();
        int ret = 0;
        int preR = 0;
        boolean preS = false;
        for (int i = 0; i < n; i++) {
            char c = directions.charAt(i);
            if (c == 'R') {
                preR++;
                preS = true;
            } else if (c == 'S') {
                ret += preR;
                preR = 0;
                preS = true;
            } else if (preR > 0) {
                ret += 2;
                ret += preR - 1;
                preR = 0;
                preS = true;
            } else if (preS) {
                ret++;
            }
        }
        return ret;
    }
}
