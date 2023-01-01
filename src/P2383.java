import java.io.*;
import java.lang.*;
import java.util.*;

// 2383. Minimum Hours of Training to Win a Competition

public class P2383 {
    public int minNumberOfHours(int ig, int ie, int[] energy, int[] experience) {
        int n = energy.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ig <= energy[i]) {
                ret += energy[i] + 1 - ig;
                ig = 1;
            } else {
                ig -= energy[i];
            }
            if (ie <= experience[i]) {
                ret += experience[i] + 1 - ie;
                ie = experience[i] + 1;
            }
            ie += experience[i];
        }
        return ret;
    }
}
