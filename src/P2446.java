import java.io.*;
import java.lang.*;
import java.util.*;

// 2446. Determine if Two Events Have Conflict

public class P2446 {
    public boolean haveConflict(String[] event1, String[] event2) {
        int[] e1 = new int[2], e2 = new int[2];
        String[] t11 = event1[0].split(":");
        String[] t12 = event1[1].split(":");
        e1[0] = Integer.parseInt(t11[0]) * 60 + Integer.parseInt(t11[1]);
        e1[1] = Integer.parseInt(t12[0]) * 60 + Integer.parseInt(t12[1]);
        String[] t21 = event2[0].split(":");
        String[] t22 = event2[1].split(":");
        e2[0] = Integer.parseInt(t21[0]) * 60 + Integer.parseInt(t21[1]);
        e2[1] = Integer.parseInt(t22[0]) * 60 + Integer.parseInt(t22[1]);
        return e1[0] <= e2[1] && e1[1] >= e2[0];
    }
}
