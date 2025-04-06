import java.util.*;

// 3080. Mark Elements on Array by Performing Queries
public class P3080 {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] marked = new boolean[n];
        long[] ret = new long[m];
        long sum = 0;
        LazyPriorityQueue<Integer> pq = new LazyPriorityQueue<>((a, b) -> {
            if (nums[a] != nums[b]) return Integer.compare(nums[a], nums[b]);
            return Integer.compare(a, b);
        });
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            pq.offer(i);
        }
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int idx = query[0], cnt = query[1];
            if (!marked[idx]) {
                marked[idx] = true;
                sum -= nums[idx];
                pq.remove(idx);
            }
            while (cnt > 0 && !pq.isEmpty()) {
                int t = pq.poll();
                sum -= nums[t];
                marked[t] = true;
                cnt--;
            }
            ret[i] = sum;
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
