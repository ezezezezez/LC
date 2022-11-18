import java.util.*;
import java.io.*;
import java.lang.*;

// 1073. Adding Two Negabinary Numbers

public class P1073 {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length;
        List<Integer> list = new ArrayList<>();

        int i = m - 1, j = n - 1;
        int jw = 0;
        while (i >= 0 && j >= 0) {
            int num = arr1[i] + arr2[j] + jw;
            if (num == 0) {
                list.add(0);
                jw = 0;
            } else if (num == 1) {
                list.add(1);
                jw = 0;
            } else if (num == 2) {
                list.add(0);
                jw = -1;
            } else if (num == 3) {
                list.add(1);
                jw = -1;
            } else {
                list.add(1);
                jw = 1;
            }
            i--; j--;
        }

        while (i >= 0) {
            int num = arr1[i] + jw;
            if (num == 0) {
                list.add(0);
                jw = 0;
            } else if (num == 1) {
                list.add(1);
                jw = 0;
            } else if (num == 2) {
                list.add(0);
                jw = -1;
            } else if (num == 3) {
                list.add(1);
                jw = -1;
            } else {
                list.add(1);
                jw = 1;
            }
            i--;
        }

        while (j >= 0) {
            int num = arr2[j] + jw;
            if (num == 0) {
                list.add(0);
                jw = 0;
            } else if (num == 1) {
                list.add(1);
                jw = 0;
            } else if (num == 2) {
                list.add(0);
                jw = -1;
            } else if (num == 3) {
                list.add(1);
                jw = -1;
            } else {
                list.add(1);
                jw = 1;
            }
            j--;
        }

        if (jw == 1) list.add(1);
        else if (jw == -1) {
            list.add(1); list.add(1);
        }
        while (!list.isEmpty() && list.get(list.size() - 1) == 0) list.remove(list.size() - 1);
        if (list.isEmpty()) return new int[]{0};

        int[] ret = new int[list.size()];
        for (int x = 0; x < ret.length; x++) {
            ret[x] = list.get(ret.length - 1 - x);
        }

        return ret;
    }
}
