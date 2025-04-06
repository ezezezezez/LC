import java.util.*;

// 3508. Implement Router
public class P3508_3 {
    int limit;
    Set<ArrayKey> set = new HashSet<>();
    Deque<ArrayKey> dq = new ArrayDeque<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    Map<Integer, Integer> begin = new HashMap<>();

    public P3508_3(int memoryLimit) {
        limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        ArrayKey ak = new ArrayKey(source, destination, timestamp);
        if (set.contains(ak)) return false;
        if (set.size() == limit) forwardPacket();
        set.add(ak);
        dq.offerLast(ak);
        map.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (set.isEmpty()) return new int[0];
        ArrayKey ak = dq.pollFirst();
        set.remove(ak);
        begin.merge(ak.getArray()[1], 1, Integer::sum);
        return ak.getArray();
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!map.containsKey(destination)) return 0;
        List<Integer> list = map.get(destination);
        int upper = upperBound(list, begin.getOrDefault(destination, 0), list.size(), endTime) - 1;
        int lower = lowerBound(list, begin.getOrDefault(destination, 0), list.size(), startTime) - 1;
        return upper - lower;
    }

    int upperBound(List<Integer> nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums.get(mid) > val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    int lowerBound(List<Integer> nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums.get(mid) >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    class ArrayKey {
        int[] array;

        ArrayKey(int... vals) {
            array = vals;
        }

        public int[] getArray() {
            return array;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(array, arrayKey.array);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }
}
