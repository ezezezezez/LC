import java.io.*;
import java.lang.*;
import java.util.*;

// 2317. Maximum XOR After Operations

public class P2317 {
    public int maximumXOR(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int num : nums) {
            ret |= num;
        }
        return ret;
    }
}
