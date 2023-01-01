import java.io.*;
import java.lang.*;
import java.util.*;

// 2240. Number of Ways to Buy Pens and Pencils

public class P2240 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ret = 0;
        for (int i = 0; cost1 * i <= total; i++) {
            int remain = total - cost1 * i;
            ret += remain / cost2 + 1;
        }
        return ret;
    }
}
