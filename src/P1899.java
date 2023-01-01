import java.io.*;
import java.lang.*;
import java.util.*;

// 1899. Merge Triplets to Form Target Triplet

public class P1899 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int n = triplets.length;
        if (triplets[0][0] == target[0] && triplets[0][1] == target[1] && triplets[0][2] == target[2]) return true;
        Set<Integer> pos1 = new HashSet<>();
        Set<Integer> pos2 = new HashSet<>();
        Set<Integer> pos3 = new HashSet<>();
        if (triplets[0][0] <= target[0] && triplets[0][1] <= target[1] && triplets[0][2] <= target[2]) {
            pos1.add(triplets[0][0]);
            pos2.add(triplets[0][1]);
            pos3.add(triplets[0][2]);
        }

        for (int i = 1; i < n; i++) {
            // System.out.println(pos1);
            // System.out.println(pos2);
            // System.out.println(pos3);
            int a = triplets[i][0], b = triplets[i][1], c = triplets[i][2];
            if (a > target[0] || b > target[1] || c > target[2]) continue;
            // System.out.println(a);
            // System.out.println(b);
            // System.out.println(c);
            if (a <= target[0] && b <= target[1] && c <= target[2]) {
                if ((a == target[0] || pos1.contains(target[0])) && (b == target[1] || pos2.contains(target[1])) && (c == target[2] || pos3.contains(target[2]))) {
                    // System.out.println(a);
                    // System.out.println(b);
                    // System.out.println(c);
                    return true;
                }
            }
            pos1.add(a);
            pos2.add(b);
            pos3.add(c);
        }
        return false;
    }
}
