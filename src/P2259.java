import java.io.*;
import java.lang.*;
import java.util.*;

// 2259. Remove Digit From Number to Maximize Result

public class P2259 {
    public String removeDigit(String number, char digit) {
        String ret = "";
        int n = number.length();
        for (int i = 0; i < n; i++) {
            char c = number.charAt(i);
            if (c == digit) {
                String t = number.substring(0, i) + number.substring(i + 1);
                if ("".equals(ret) || t.compareTo(ret) > 0) {
                    ret = t;
                }
            }
        }
        return ret;
    }
}
