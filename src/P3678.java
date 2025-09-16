import java.util.HashSet;
import java.util.Set;

// 3678. Smallest Absent Positive Greater Than Average

public class P3678 {
    public int smallestAbsent(int[] nums) {
        int n = nums.length;
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            sum += num;
            set.add(num);
        }
        double avg = sum / n;
        for (int i = 1; i <= 200; i++) {
            if (!set.contains(i) && i > avg) return i;
        }
        return -1;
    }
}
