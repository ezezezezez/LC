import java.io.*;
import java.lang.*;
import java.util.*;

// 2303. Calculate Amount Paid in Taxes

public class P2303 {
    public double calculateTax(int[][] brackets, int income) {
        double ret = 0;
        int pre = 0;
        for (int[] bracket : brackets) {
            int upper = bracket[0] - pre, percent = bracket[1];
            if (upper < income) {
                income -= upper;
                ret += upper * 1.0 * percent / 100;
            } else {
                ret += income * 1.0 * percent / 100;
                income = 0;
                break;
            }
            pre = bracket[0];
        }
        return ret;
    }
}
