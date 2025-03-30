import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 3490. Count Beautiful Numbers
public class P3490_2 {
    // A class to represent a DP state.
    private static class State {
        int pos;
        boolean tight;
        boolean started;
        boolean hasZero;
        int sum;
        int prod;

        public State(int pos, boolean tight, boolean started, boolean hasZero, int sum, int prod) {
            this.pos = pos;
            this.tight = tight;
            this.started = started;
            this.hasZero = hasZero;
            this.sum = sum;
            this.prod = prod;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State state = (State) o;
            return pos == state.pos &&
                    tight == state.tight &&
                    started == state.started &&
                    hasZero == state.hasZero &&
                    sum == state.sum &&
                    prod == state.prod;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos, tight, started, hasZero, sum, prod);
        }
    }

    // Count beautiful numbers in [1, n].
    private long countBeautiful(int n) {
        if (n < 1) {
            return 0;
        }
        // Convert number to digits array
        String s = Integer.toString(n);
        int[] digits = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digits[i] = s.charAt(i) - '0';
        }
        // Use a HashMap for memoization.
        HashMap<State, Long> memo = new HashMap<>();
        return dp(0, true, false, false, 0, 1, digits, memo);
    }

    /**
     * Recursive DP function.
     *
     * @param pos    Current digit position.
     * @param tight  If true, the current prefix is equal to the prefix of n.
     * @param started If true, we have started forming the number (i.e. a non-leading zero has been placed).
     * @param hasZero If true, at least one digit 0 (after starting) has been encountered.
     * @param sum    The sum of the digits so far.
     * @param prod   The product of the digits so far (if no zero encountered, otherwise 0).
     * @param digits The array of digits of n.
     * @param memo   Memoization cache.
     * @return Count of valid beautiful completions.
     */
    private long dp(int pos, boolean tight, boolean started, boolean hasZero, int sum, int prod, int[] digits, HashMap<State, Long> memo) {
        // Base condition: if we have processed all digits.
        if (pos == digits.length) {
            // If no digit was chosen, return 0 (we want only positive integers)
            if (!started) return 0;
            // If a zero was encountered, the number is automatically beautiful.
            if (hasZero) return 1;
            // Otherwise, check if product is divisible by sum.
            return (prod % sum == 0) ? 1 : 0;
        }

        // Create the state for memoization.
        State state = new State(pos, tight, started, hasZero, sum, prod);
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        long total = 0;
        // Determine the upper bound for the current digit.
        int up = tight ? digits[pos] : 9;
        for (int d = 0; d <= up; d++) {
            boolean newTight = tight && (d == up);
            if (!started) {
                if (d == 0) {
                    // Still in leading zeros; we don't update sum or product.
                    total += dp(pos + 1, newTight, false, false, 0, 1, digits, memo);
                } else {
                    // Start the number with a nonzero digit.
                    total += dp(pos + 1, newTight, true, false, d, d, digits, memo);
                }
            } else {
                int newSum = sum + d;
                boolean newHasZero = hasZero || (d == 0);
                int newProd = newHasZero ? 0 : prod * d;
                total += dp(pos + 1, newTight, true, newHasZero, newSum, newProd, digits, memo);
            }
        }
        memo.put(state, total);
        return total;
    }

    // Returns count of beautiful numbers in [l, r]
    public int beautifulNumbers(int l, int r) {
        return (int) (countBeautiful(r) - countBeautiful(l - 1));
    }
}
