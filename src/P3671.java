import java.util.*;

// 3671. Sum of Beautiful Subsequences
public class P3671 {
    static int MAXV = 70001;
    static List<Integer>[] facList = new List[MAXV];
    static List<Integer>[] facListMulti = new List[MAXV];
    static List<Integer>[] divisors = new List[MAXV];
    static boolean initialized = false;
    static void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(facList, i -> new ArrayList<>());
        Arrays.setAll(facListMulti, i -> new ArrayList<>());
        Arrays.setAll(divisors, i -> new ArrayList<>());

        int[] spf = new int[MAXV];
        for (int i = 2; i < MAXV; i++) {
            if (spf[i] == 0) {
                for (int j = i; j < MAXV; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }

        for (int x = 2; x < MAXV; x++) {
            int t = x;
            while (t > 1) {
                int p = spf[t];
                facListMulti[x].add(p);
                if (facList[x].isEmpty() || facList[x].get(facList[x].size() - 1) != p) {
                    facList[x].add(p);
                }
                t /= p;
            }
        }

        for (int i = 1; i < MAXV; i++) {
            for (int j = i; j < MAXV; j += i) {
                divisors[j].add(i);
            }
        }
    }

    int mod = 1000000007;
    public int totalBeauty(int[] nums) {
        init();
        int n = nums.length;
        int mx = 0;
        for (int v : nums) mx = Math.max(mx, v);
        List<Integer>[] buckets = new List[mx + 1];
        Arrays.setAll(buckets, kk -> new ArrayList<>());
        for (int v : nums) {
            for (int d : divisors[v]) {
                buckets[d].add(v);
            }
        }
        int[] countDiv = new int[mx + 1];
        for (int g = 1; g <= mx; g++) {
            List<Integer> bucket = buckets[g];
            if (bucket.isEmpty()) {
                continue;
            }
            int k = bucket.size();
            int[] temp = new int[k];
            for (int i = 0; i < k; i++) {
                temp[i] = bucket.get(i);
            }
            Arrays.sort(temp);
            int p = 1;
            for (int i = 1; i < k; i++) {
                if (temp[i] != temp[i - 1]) {
                    temp[p++] = temp[i];
                }
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < p; i++) {
                map.put(temp[i], i + 1);
            }
            BIT1 bit = new BIT1(p);
            long cnt = 0;
            for (int i = 0; i < k; i++) {
                int idx = map.get(bucket.get(i));
                long cur = bit.query(1, idx - 1) + 1;
                bit.update(idx, cur);
                cnt += cur;
                if (cnt >= mod) cnt -= mod;
            }
            countDiv[g] = (int) cnt;
        }

        int[] countExact = inversionMod(countDiv, mx, mod);
        // System.out.println(Arrays.toString(countDiv));
        // System.out.println(Arrays.toString(countExact));
        long ret = 0;
        for (int g = 1; g <= mx; g++) {
            ret += 1L * g * countExact[g];
            ret %= mod;
        }
        return (int) ret;
    }

    public int[] mobiusSieve(int N) {
        int[] mu = new int[N + 1];
        int[] primes = new int[N + 1];
        boolean[] isComp = new boolean[N + 1];
        int pc = 0;
        mu[1] = 1;
        for (int i = 2; i <= N; ++i) mu[i] = 0;
        for (int i = 2; i <= N; ++i) {
            if (!isComp[i]) {
                primes[pc++] = i;
                mu[i] = -1;
            }
            for (int j = 0; j < pc; ++j) {
                int p = primes[j];
                long v = 1L * p * i;
                if (v > N) break;
                isComp[(int) v] = true;
                if (i % p == 0) {
                    mu[(int) v] = 0;
                    break;
                } else {
                    mu[(int) v] = -mu[i];
                }
            }
        }
        mu[1] = 1;
        return mu;
    }

    int[] inversionMod(int[] countDiv, int mx, int mod) {
        int[] mu = mobiusSieve(mx);
        int[] countExact = new int[mx + 1];
        for (int g = 1; g <= mx; g++) {
            int v = 0;
            for (int t = 1, tg = g; tg <= mx; t++, tg += g) {
                v += mu[t] * countDiv[tg];
                if (v >= mod) v -= mod;
                if (v < 0) v += mod;
            }
            countExact[g] = v;
        }
        return countExact;
    }

    static class BIT1 {
        long[] tree;
        int n;
        int MX_IDX;
        int mod = 1000000007;

        BIT1(int n) {
            this.n = n;
            tree = new long[n + 1];
            while ((1 << MX_IDX) <= n) MX_IDX++;
        }

        // x: 1-based
        void update(int index, long delta) {
            if (index <= 0 || index > n)
                throw new IllegalArgumentException(String.format("index(%s) should be in [1, n(%s)]", index, n));
            for (int i = index; i <= n; i += lowbit(i)) {
                tree[i] += delta;
                if (tree[i] >= mod) tree[i] -= mod;
            }
        }

        // sum of val[1...r]
        private long query(int r) {
            if (r < 0 || r > n) throw new IllegalArgumentException(String.format("r(%s) should be in [0, n(%s)]", r, n));
            long ret = 0;
            for (int i = r; i > 0; i -= lowbit(i)) {
                ret += tree[i];
                if (ret >= mod) ret -= mod;
            }
            return ret;
        }

        // l, r: 1-based
        long query(int l, int r) {
            if (l > r) return 0;
            long ret = query(r) - query(l - 1);
            if (ret < 0) ret += mod;
            return ret;
        }

        // return: 0: BIT is empty/not found, others: kth value
        int findKth(int k) {
            int pos = 0;
            long cnt = 0;
            for (int i = MX_IDX; i >= 0; i--) {
                int bit = 1 << i;
                int nxtPos = pos + bit;
                if (nxtPos <= n && cnt + tree[nxtPos] < k) {
                    pos = nxtPos;
                    cnt += tree[nxtPos];
                    if (cnt >= mod) cnt -= mod;
                }
            }
            return pos == n ? 0 : (pos + 1);
        }

        int lowbit(int x) {
            return x & -x;
        }
    }
}
