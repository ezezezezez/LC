import java.io.*;
import java.lang.*;
import java.util.*;

// 1813. Sentence Similarity III

public class P1813 {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] tokens1 = sentence1.split(" "), tokens2 = sentence2.split(" ");
        int n = tokens1.length, m = tokens2.length;
        if (n >= m) {
            int i = 0;
            while (i < m && tokens1[i].equals(tokens2[i])) {
                i++;
            }
            int j = m - i;
            while (j > 0 && tokens1[n - j].equals(tokens2[m - j])) {
                j--;
            }
            return j == 0;
        } else {
            int i = 0;
            while (i < n && tokens1[i].equals(tokens2[i])) {
                i++;
            }
            int j = n - i;
            while (j > 0 && tokens1[n - j].equals(tokens2[m - j])) {
                j--;
            }
            return j == 0;
        }
    }
}
