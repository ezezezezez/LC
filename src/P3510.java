import java.util.*;

// 3510. Minimum Pair Removal to Sort Array II
public class P3510 {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        Node head = null;
        Node tail = null;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (head == null) {
                head = nodes[i];
                tail = nodes[i];
            } else {
                tail.next = nodes[i];
                nodes[i].prev = tail;
                tail = nodes[i];
            }
        }

        int count = 0;
        Node current = head;
        while (current != null && current.next != null) {
            if (current.value > current.next.value) {
                count++;
            }
            current = current.next;
        }
        if (count == 0) return 0;

        LazyPriorityQueue<Pair> heap = new LazyPriorityQueue<>((a, b) -> {
            if (a.sum != b.sum) {
                return Long.compare(a.sum, b.sum);
            } else {
                return Long.compare(a.leftIndex, b.leftIndex);
            }
        });

        current = head;
        while (current != null && current.next != null) {
            Node next = current.next;
            heap.offer(new Pair(current.value + next.value, current.leftIndex, current, next));
            current = current.next;
        }

        int operations = 0;

        while (count > 0) {
            Pair pair = heap.poll();
            Node x = pair.x;
            Node y = pair.y;

            operations++;

            Node a = x.prev;
            Node b = y.next;

            if (a != null && a.value > x.value) {
                count--;
            }
            if (x.value > y.value) {
                count--;
            }
            if (b != null && y.value > b.value) {
                count--;
            }

            Node z = new Node(x.value + y.value, x.leftIndex);
            z.prev = a;
            z.next = b;

            if (a != null) {
                a.next = z;
            }
            if (b != null) {
                b.prev = z;
            }

            if (a != null) {
                if (a.value > z.value) {
                    count++;
                }
                heap.remove(new Pair(a.value + x.value, a.leftIndex, a, x));
                heap.offer(new Pair(a.value + z.value, a.leftIndex, a, z));
            }
            if (b != null) {
                if (z.value > b.value) {
                    count++;
                }
                heap.remove(new Pair(y.value + b.value, y.leftIndex, y, b));
                heap.offer(new Pair(z.value + b.value, z.leftIndex, z, b));
            }
        }

        return operations;
    }

    class Node {
        long value;
        int leftIndex;
        Node prev;
        Node next;

        Node(long value, int leftIndex) {
            this.value = value;
            this.leftIndex = leftIndex;
            prev = next = null;
        }

        public String toString() {
            return String.format("[%s, %s]", value, leftIndex);
        }
    }

    class Pair {
        long sum;
        int leftIndex;
        Node x;
        Node y;

        Pair(long sum, int leftIndex, Node x, Node y) {
            this.sum = sum;
            this.leftIndex = leftIndex;
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return x +  " " + y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;
            return x.equals(pair.x) && y.equals(pair.y);
        }

        @Override
        public int hashCode() {
            int result = x.hashCode();
            result = 31 * result + y.hashCode();
            return result;
        }
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
