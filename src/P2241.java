import java.io.*;
import java.lang.*;
import java.util.*;

// 2241. Design an ATM Machine

public class P2241 {
    long a = 0;
    long b = 0;
    long c = 0;
    long d = 0;
    long e = 0;

    public P2241() {

    }

    public void deposit(int[] bc) {
        a += bc[0];
        b += bc[1];
        c += bc[2];
        d += bc[3];
        e += bc[4];
        // System.out.println(a + " " + b + " " + c + " " + d + " " + e);
    }

    public int[] withdraw(int amount) {

        long[] ret = new long[5];
        if (e > 0) {
            ret[4] = Math.min(e, amount / 500);
            amount -= ret[4] * 500;
        }
        if (d > 0) {
            ret[3] = Math.min(d, amount / 200);
            amount -= ret[3] * 200;
        }
        if (c > 0) {
            ret[2] = Math.min(c, amount / 100);
            amount -= ret[2] * 100;
        }
        if (b > 0) {
            ret[1] = Math.min(b, amount / 50);
            amount -= ret[1] * 50;
        }
        if (a > 0) {
            ret[0] = Math.min(a, amount / 20);
            amount -= ret[0] * 20;
        }
        if (amount == 0) {
            a -= ret[0];
            b -= ret[1];
            c -= ret[2];
            d -= ret[3];
            e -= ret[4];
            int[] rret = new int[5];
            rret[0] = (int)ret[0];
            rret[1] = (int)ret[1];
            rret[2] = (int)ret[2];
            rret[3] = (int)ret[3];
            rret[4] = (int)ret[4];
            return rret;
        }
        return new int[]{-1};
    }
}
