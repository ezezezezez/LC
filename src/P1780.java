import java.io.*;
import java.lang.*;
import java.util.*;

// 1780. Check if Number is a Sum of Powers of Three

public class P1780 {
    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            while (n % 3 == 0) n /= 3;
            if (n % 3 != 1) return false;
            n--;
        }
        return true;
    }
}
