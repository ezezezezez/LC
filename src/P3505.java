import java.util.*;

// 3504. Longest Palindrome After Substring Concatenation II
public class P3505 {
    long INF = (long) 1e13;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costArr = getCostArray(nums, x);
        long[][] dp = new long[k + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, INF);
        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (j < x - 1) continue;
                dp[i][j + 1] = dp[i][j];
                if (j >= x - 1) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - 1][j + 1 - x] + costArr[j]);
                }
            }
        }
        return dp[k][n];
    }

    public long[] getCostArray(int[] nums, int x) {
        int n = nums.length;
        LazyPriorityQueue left = new LazyPriorityQueue(Collections.reverseOrder());
        LazyPriorityQueue right = new LazyPriorityQueue();

        long[] ret = new long[n];

        for (int j = 0; j < n; j++) {
            if (right.isEmpty() || nums[j] >= right.peek()) {
                right.offer(nums[j]);
            } else {
                left.offer(nums[j]);
            }

            if (j < x - 1) continue;

            if (j >= x) {
                int t = nums[j - x];
                if (!left.isEmpty() && t <= left.peek()) {
                    left.remove(t);
                } else {
                    right.remove(t);
                }
            }

            while (right.size() > left.size() + 1) {
                int v = right.peek();
                right.remove(v);
                left.offer(v);
            }
            while (right.size() < left.size()) {
                int v = left.peek();
                left.remove(v);
                right.offer(v);
            }

            if (j >= x - 1) {
                int median = right.peek();
                long cost = right.sum() - (long) median * right.size() + (long) median * left.size() - left.sum();
                ret[j] = cost;
            }
        }
        return ret;
    }

    class LazyPriorityQueue {
        private final PriorityQueue<Integer> minHeap;
        private final HashMap<Integer, Integer> lazyDeletions;
        private int size;
        private long totalSum;

        public LazyPriorityQueue() {
            minHeap = new PriorityQueue<>();
            lazyDeletions = new HashMap<>();
            size = 0;
            totalSum = 0;
        }

        public LazyPriorityQueue(Comparator<Integer> comparator) {
            minHeap = new PriorityQueue<>(comparator);
            lazyDeletions = new HashMap<>();
            size = 0;
            totalSum = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public long sum() {
            return totalSum;
        }

        public void offer(int element) {
            minHeap.offer(element);
            size++;
            totalSum += element;
        }

        public void remove(int element) {
            lazyDeletions.put(element, lazyDeletions.getOrDefault(element, 0) + 1);
            size--;
            totalSum -= element;
        }

        private void cleanHeap() {
            while (!minHeap.isEmpty()) {
                int top = minHeap.peek();
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

        public int peek() {
            cleanHeap();
            if (minHeap.isEmpty()) {
                throw new RuntimeException("Priority queue is empty.");
            }
            return minHeap.peek();
        }

        public Integer poll() {
            cleanHeap();
            if (minHeap.isEmpty()) {
                return null;
            }
            int top = minHeap.poll();
            size--;
            totalSum -= top;
            return top;
        }

        @Override
        public String toString() {
            return "LazyPriorityQueue{" +
                    "minHeap=" + minHeap +
                    ", lazyDeletions=" + lazyDeletions +
                    ", size=" + size +
                    ", totalSum=" + totalSum +
                    '}';
        }
    }
}
