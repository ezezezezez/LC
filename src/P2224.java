import java.io.*;
import java.lang.*;
import java.util.*;

// 2224. Minimum Number of Operations to Convert Time

public class P2224 {
    public int convertTime(String current, String correct) {
        int c = Integer.parseInt(current.substring(0, 2)) * 60 + Integer.parseInt(current.substring(3));
        int t = Integer.parseInt(correct.substring(0, 2)) * 60 + Integer.parseInt(correct.substring(3));
        int diff = t - c;
        int ret = 0;
        ret += diff / 60;
        diff %= 60;
        ret += diff / 15;
        diff %= 15;
        ret += diff / 5;
        diff %= 5;
        ret += diff / 1;
        diff %= 1;
        return ret;
    }
}
