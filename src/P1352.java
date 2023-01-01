import java.util.*;
import java.io.*;
import java.lang.*;

// 1352. Product of the Last K Numbers

public class P1352 {
    int prod = 1;
    List<Integer> nums = new ArrayList<>();

    public P1352() {

    }

    public void add(int num) {
        if (num == 0) {
            nums.clear();
            return;
        }
        nums.add(nums.isEmpty() ? num : num * nums.get(nums.size() - 1));
    }

    public int getProduct(int k) {
        if (k > nums.size()) return 0;
        return nums.get(nums.size() - 1) / (nums.size() == k ? 1 : nums.get(nums.size() - k - 1));
    }
}
