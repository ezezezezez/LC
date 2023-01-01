import java.io.*;
import java.lang.*;
import java.util.*;

// 2481. Minimum Cuts to Divide a Circle

public class P2481 {
    public int numberOfCuts(int n) {
        if (n == 1) return 0;
        return n % 2 == 0 ? n / 2 : n;
    }
}
