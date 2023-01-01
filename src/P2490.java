import java.io.*;
import java.lang.*;
import java.util.*;

// 2490. Circular Sentence

public class P2490 {
    public boolean isCircularSentence(String sentence) {
        String[] tokens = sentence.split(" ");
        int n = tokens.length;
        for (int i = 1; i < n; i++) {
            if (tokens[i].charAt(0) != tokens[i - 1].charAt(tokens[i - 1].length() - 1)) {
                return false;
            }
        }
        return tokens[0].charAt(0) == tokens[n - 1].charAt(tokens[n - 1].length() - 1);
    }
}
