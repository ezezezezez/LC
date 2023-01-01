import java.io.*;
import java.lang.*;
import java.util.*;

// 1395. Count Number of Teams
public class P1395 {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            int v = rating[i];
            int leftCnt = 0, rightCnt = 0;
            for (int j = 0; j < i; j++) {
                if (rating[j] < v) leftCnt++;
            }
            for (int j = i + 1; j < n; j++) {
                if (rating[j] > v) rightCnt++;
            }
            ret += leftCnt * rightCnt;
        }

        for (int i = 0; i < n; i++) {
            int v = rating[i];
            int leftCnt = 0, rightCnt = 0;
            for (int j = 0; j < i; j++) {
                if (rating[j] > v) leftCnt++;
            }
            for (int j = i + 1; j < n; j++) {
                if (rating[j] < v) rightCnt++;
            }
            ret += leftCnt * rightCnt;
        }
        return ret;
    }
}
