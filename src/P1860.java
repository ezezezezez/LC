import java.io.*;
import java.lang.*;
import java.util.*;

// 1860. Incremental Memory Leak

public class P1860 {
    public int[] memLeak(int memory1, int memory2) {
        int a = 0, b = 0, time = 0;
        while (a + time + 1 <= memory1 || b + time + 1 <= memory2) {
            if (memory1 - a == memory2 - b) {
                a += time + 1;
            } else if (memory1 - a > memory2 - b) {
                a += time + 1;
            } else {
                b += time + 1;
            }
            time++;
        }
        return new int[]{time + 1, memory1 - a, memory2 - b};
    }
}
