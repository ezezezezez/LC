import java.io.*;
import java.lang.*;
import java.util.*;

// 1750. Minimum Length of String After Deleting Similar Ends

public class P1750 {
    public int minimumLength(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        while (j - i + 1 >= 2) {
            if (s.charAt(i) != s.charAt(j)) break;
            char c = s.charAt(i);
            while (i <= j && s.charAt(i) == c) i++;
            while (i <= j && s.charAt(j) == c) j--;
        }
        return j - i + 1;
    }
}
