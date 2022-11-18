import java.util.*;
import java.io.*;
import java.lang.*;

// 950. Reveal Cards In Increasing Order

public class P950 {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] ret = new int[n];
        Arrays.sort(deck);

        int cnt = 0;
        int pos = 0, idx = 0;
        boolean flag = false;
        while (cnt != n) {
            if (ret[pos] == 0) {
                flag = !flag;
                if (flag) {
                    ret[pos] = deck[idx++];
                    cnt++;
                }
            }
            pos = (pos + 1) % n;
        }

        return ret;
    }
}
