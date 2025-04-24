import java.util.*;

// 3092. Most Frequent IDs
public class P3092 {
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        int n = nums.length;
        long[] ret = new long[n];
        Map<Integer, Long> map = new HashMap<>();
        LazyPriorityQueue<List<Long>> pq = new LazyPriorityQueue<>((a, b) -> Long.compare(b.get(1), a.get(1)));
        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(nums[i], 0L) == 0L) {
                pq.offer(List.of((long) nums[i], (long) freq[i]));
            } else {
                pq.remove(List.of((long) nums[i], map.get(nums[i])));
                pq.offer(List.of((long) nums[i], map.get(nums[i]) + freq[i]));
            }
            map.merge(nums[i], (long) freq[i], Long::sum);
            ret[i] = pq.peek().get(1);
            // System.out.println(map);
            // System.out.println(pq);
        }
        return ret;
    }

    class LazyPriorityQueue<T> {
        private final PriorityQueue<T> minHeap;
        private final HashMap<T, Integer> lazyDeletions;
        private int size;

        public LazyPriorityQueue() {
            minHeap = new PriorityQueue<>();
            lazyDeletions = new HashMap<>();
            size = 0;
        }

        public LazyPriorityQueue(Comparator<T> comparator) {
            minHeap = new PriorityQueue<>(comparator);
            lazyDeletions = new HashMap<>();
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(T element) {
            minHeap.offer(element);
            size++;
        }

        public void remove(T element) {
            lazyDeletions.put(element, lazyDeletions.getOrDefault(element, 0) + 1);
            size--;
        }

        private void cleanHeap() {
            while (!minHeap.isEmpty()) {
                T top = minHeap.peek();
                if (lazyDeletions.getOrDefault(top, 0) > 0) {
                    lazyDeletions.put(top, lazyDeletions.get(top) - 1);
                    if (lazyDeletions.get(top) == 0) {
                        lazyDeletions.remove(top);
                    }
                    minHeap.poll();
                } else {
                    break;
                }
            }
        }

        public T peek() {
            cleanHeap();
            if (minHeap.isEmpty()) {
                throw new RuntimeException("Priority queue is empty.");
            }
            return minHeap.peek();
        }

        public T poll() {
            cleanHeap();
            if (minHeap.isEmpty()) {
                return null;
            }
            T top = minHeap.poll();
            size--;
            return top;
        }

        @Override
        public String toString() {
            return "LazyPriorityQueue{" +
                    "minHeap=" + minHeap +
                    ", lazyDeletions=" + lazyDeletions +
                    ", size=" + size +
                    '}';
        }
    }
}
