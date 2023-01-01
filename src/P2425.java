import java.io.*;
import java.lang.*;
import java.util.*;

// 2425. Bitwise XOR of All Pairings

public class P2425 {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int a = 0, b = 0;
        for (int num : nums1) a ^= num;
        for (int num : nums2) b ^= num;
        int aa = 0, bb = 0;
        for (int i = 0; i < m; i++) aa ^= a;
        for (int i = 0; i < n; i++) bb ^= b;
        return aa ^ bb;
    }
}
