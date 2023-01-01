import java.io.*;
import java.lang.*;
import java.util.*;

// 2485. Find the Pivot Integer

public class P2485 {
    public int pivotInteger(int n) {
        int x = -1;
        for (int i = 1; i <= n; i++) {
            if (i * (1 + i) / 2 == (n - i + 1) * (i + n) / 2) {
                x = i;
                break;
            }
        }
        return x;
    }
}
