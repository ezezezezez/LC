import java.io.*;
import java.lang.*;
import java.util.*;

// 2413. Smallest Even Multiple

public class P2413 {
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }
}
