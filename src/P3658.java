// 3658. GCD of Odd and Even Sums
public class P3658 {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 0, sumEven = 0;
        for (int i = 0; i < n; i++) {
            sumOdd += 2 * i + 1;
            sumEven += 2 * i + 2;
        }
        return gcd(sumOdd, sumEven);
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
