// 3185. Count Pairs That Form a Complete Day II
public class P3185 {
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        long ret = 0;
        for (int num : hours) {
            num %= 24;
            if (num == 0) {
                ret += map.getOrDefault(0, 0);
            } else {
                ret += map.getOrDefault(24 - num, 0);
            }
            map.merge(num, 1, Integer::sum);
        }
        return ret;
    }
}
