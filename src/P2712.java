import java.util.HashSet;
import java.util.Set;

// 2712. Minimum Cost to Make All Characters Equal
public class P2712 {
    public long minimumCost(String s) {
        long ret = Long.MAX_VALUE;
        int n = s.length();
        if (n % 2 == 0) {
            long leftB = 0, leftW = 0, rightB = 0, rightW = 0;
            int leftFlip = 0;
            for (int i = n / 2 - 1; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 0) {
                    leftFlip ^= 1;
                    leftB += i + 1;
                }
            }
            leftFlip = 0;
            for (int i = n / 2 - 1; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 1) {
                    leftFlip ^= 1;
                    leftW += i + 1;
                }
            }
            int rightFlip = 0;
            for (int i = n / 2; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 0) {
                    rightFlip ^= 1;
                    rightB += n - i;
                }
            }
            rightFlip = 0;
            for (int i = n / 2; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 1) {
                    rightFlip ^= 1;
                    rightW += n - i;
                }
            }
            // System.out.println(leftB + " " + rightB + " " + leftW + " " + rightW);
            return Math.min(Math.min(leftB + rightB, leftW + rightW), Math.min(leftB + rightW, leftW + rightB) + n / 2);
        } else {
            long leftB = 0, leftW = 0, rightB = 0, rightW = 0;
            int leftFlip = 0;
            for (int i = n / 2 - 1; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 0) {
                    leftFlip ^= 1;
                    leftB += i + 1;
                }
            }
            leftFlip = 0;
            for (int i = n / 2 - 1; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 1) {
                    leftFlip ^= 1;
                    leftW += i + 1;
                }
            }
            int rightFlip = 0;
            for (int i = n / 2; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 0) {
                    rightFlip ^= 1;
                    rightB += n - i;
                }
            }
            rightFlip = 0;
            for (int i = n / 2; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 1) {
                    rightFlip ^= 1;
                    rightW += n - i;
                }
            }
            long t1 = Math.min(Math.min(leftB + rightB, leftW + rightW), Math.min(leftB + rightW, leftW + rightB) + n / 2);

            leftB = 0;
            leftW = 0;
            rightB = 0;
            rightW = 0;
            leftFlip = 0;
            for (int i = n / 2; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 0) {
                    leftFlip ^= 1;
                    leftB += i + 1;
                }
            }
            leftFlip = 0;
            for (int i = n / 2; i >= 0; i--) {
                int v = s.charAt(i) - '0';
                if ((v ^ leftFlip) == 1) {
                    leftFlip ^= 1;
                    leftW += i + 1;
                }
            }
            rightFlip = 0;
            for (int i = n / 2 + 1; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 0) {
                    rightFlip ^= 1;
                    rightB += n - i;
                }
            }
            rightFlip = 0;
            for (int i = n / 2 + 1; i < n; i++) {
                int v = s.charAt(i) - '0';
                if ((v ^ rightFlip) == 1) {
                    rightFlip ^= 1;
                    rightW += n - i;
                }
            }
            long t2 = Math.min(Math.min(leftB + rightB, leftW + rightW), Math.min(leftB + rightW, leftW + rightB) + n / 2);
            return Math.min(t1, t2);
        }
    }
}
