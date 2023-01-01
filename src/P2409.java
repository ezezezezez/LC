import java.io.*;
import java.lang.*;
import java.util.*;

// 2409. Count Days Spent Together

public class P2409 {
    public int countDaysTogether(String aa, String la, String ab, String lb) {
        int ret = 0;
        int[] monthDates = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int aam = Integer.parseInt(aa.substring(0, 2));
        int bam = Integer.parseInt(ab.substring(0, 2));
        // System.out.println(aam + " " + bam);
        int aad = Integer.parseInt(aa.substring(3));
        int bad = Integer.parseInt(ab.substring(3));
        // System.out.println(aad + " " + bad);
        int alm = Integer.parseInt(la.substring(0, 2));
        int blm = Integer.parseInt(lb.substring(0, 2));
        // System.out.println(alm + " " + blm);
        int ald = Integer.parseInt(la.substring(3));
        int bld = Integer.parseInt(lb.substring(3));
        // System.out.println(ald + " " + bld);
        int as = 0, ae = 0, bs = 0, be = 0;
        for (int i = 0; i < aam - 1; i++) {
            as += monthDates[i];
        }
        for (int i = 0; i < bam - 1; i++) {
            bs += monthDates[i];
        }
        for (int i = 0; i < alm - 1; i++) {
            ae += monthDates[i];
        }
        for (int i = 0; i < blm - 1; i++) {
            be += monthDates[i];
        }
        // System.out.println(ald + " " + bad);
        as += aad;
        ae += ald;
        bs += bad;
        be += bld;
        // System.out.println(as + " " + ae);
        // System.out.println(bs + " " + be);
        return Math.max(0, Math.min(ae, be) - Math.max(as, bs) + 1);
    }
}
