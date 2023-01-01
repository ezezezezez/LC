import java.io.*;
import java.lang.*;
import java.util.*;

// 2347. Best Poker Hand

public class P2347 {
    public String bestHand(int[] ranks, char[] suits) {
        char aa = suits[0];
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            if (suits[i] != aa) {
                flag = false;
                break;
            }
        }
        if (flag) return "Flush";
        Map<Integer, Integer> map = new HashMap<>();
        for (int rank : ranks) {
            map.merge(rank, 1, Integer::sum);
        }
        for (int key : map.keySet()) {
            if (map.get(key) >= 3) return "Three of a Kind";
        }
        for (int key : map.keySet()) {
            if (map.get(key) >= 2) return "Pair";
        }
        return "High Card";
    }
}
