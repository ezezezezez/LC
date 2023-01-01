import java.io.*;
import java.lang.*;
import java.util.*;

// 1798. Maximum Number of Consecutive Values You Can Make
// see https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/solution/ni-neng-gou-zao-chu-lian-xu-zhi-de-zui-d-hlxf/

public class P1798 {
    public int getMaximumConsecutive(int[] coins) {
        int n = coins.length;
        Arrays.sort(coins);
        int nxt = 0;
        for (int coin : coins) {
            if (nxt + 1 >= coin) {
                nxt += coin;
            } else {
                break;
            }
        }
        return nxt + 1;
    }
}
