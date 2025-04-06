import java.util.*;

// 3508. Implement Router
public class P3508_2 {
    int mem;
    Set<ArrayKey> packets = new HashSet<>();
    Deque<ArrayKey> dq = new ArrayDeque<>();
    Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
    Map<Integer, Integer> head = new HashMap<>();
    Map<Integer, Integer> popCount = new HashMap<>();

    public P3508_2(int memoryLimit) {
        mem = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        ArrayKey ak = new ArrayKey(source, destination, timestamp);
        if (packets.contains(ak)) return false;
        if (packets.size() == mem) forwardPacket();
        packets.add(ak);
        dq.offerLast(ak);
        TreeMap<Integer, Integer> tm = map.computeIfAbsent(destination, k -> new TreeMap<>());
        tm.put(timestamp, head.getOrDefault(destination, 0) + 1);
        head.merge(destination, 1, Integer::sum);
        return true;
    }

    public int[] forwardPacket() {
        if (packets.isEmpty()) return new int[0];
        ArrayKey ak = dq.pollFirst();
        packets.remove(ak);
        popCount.merge(ak.getArray()[1], 1, Integer::sum);
        return ak.getArray();
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!map.containsKey(destination)) return 0;
        TreeMap<Integer, Integer> tm = map.get(destination);
        Integer upperKey = tm.floorKey(endTime);
        if (upperKey == null) return 0;
        int upper = tm.get(upperKey);
        Integer lowerKey = tm.lowerKey(startTime);
        int lower = 0;
        if (lowerKey != null) lower = tm.get(lowerKey);
        int popNum = popCount.getOrDefault(destination, 0);
        // System.out.println(upper + " " + lower + " " + popNum + " " + tm);
        return Math.max(0, upper - Math.max(lower, popNum));
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
