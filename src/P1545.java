import java.io.*;
import java.lang.*;
import java.util.*;

// 1545. Find Kth Bit in Nth Binary String

public class P1545 {
    public char findKthBit(int n, int k) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 2; i <= n; i++) {
            StringBuilder temp = new StringBuilder(sb);
            int len = temp.length();
            for (int j = 0; j < len; j++) {
                temp.setCharAt(j, temp.charAt(j) == '0' ? '1' : '0');
            }
            sb.append('1');
            sb.append(temp.reverse());
        }
        // System.out.println(sb);
        return sb.charAt(k - 1);
    }
}
