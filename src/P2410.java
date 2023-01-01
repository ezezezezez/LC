import java.io.*;
import java.lang.*;
import java.util.*;

// 2410. Maximum Matching of Players With Trainers

public class P2410 {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int n = players.length, m = trainers.length;
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && trainers[j] < players[i]) j++;
            if (j == m) break;
            ret++;
            j++;
        }
        return ret;
    }
}
