import java.util.*;
import java.io.*;
import java.lang.*;

// 1227. Airplane Seat Assignment Probability

public class P1227 {
    public double nthPersonGetsNthSeat(int n) {
        double ret = 0;
        double cur = 1;
        for (int i = n; i > 1; i--) {
            cur *= 1.0 / i;
            ret += cur;
            cur = ret;
        }
        return 1 - ret;
    }
}
