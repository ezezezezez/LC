import java.util.*;

// 2766. Relocate Marbles
public class P2766 {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        List<Integer> ret = new ArrayList<>();
        int n = nums.length, m = moveFrom.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int i = 0; i < m; i++) {
            int from = moveFrom[i], to = moveTo[i];
            set.remove(from);
            set.add(to);
        }
        ret.addAll(set);
        Collections.sort(ret);
        return ret;
    }
}
