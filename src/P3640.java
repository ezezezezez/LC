import java.util.ArrayList;
import java.util.List;

// 3640. Trionic Array II

public class P3640 {
    long INF = (long) -1e15;

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long ret = INF;
        List<List<Integer>> inc = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) j++;
            if (j - 1 != i) {
                inc.add(List.of(i, j - 1));
            }
            i = j - 1;
        }
        List<List<Integer>> dec = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] < nums[j - 1]) j++;
            if (j - 1 != i) {
                dec.add(List.of(i, j - 1));
            }
            i = j - 1;
        }
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // System.out.println(inc);
        // System.out.println(dec);
        int j = 0, k = 1;
        for (int i = 0; i < inc.size(); i++) {
            List<Integer> firstInc = inc.get(i);
            int fs = firstInc.get(0), fe = firstInc.get(1);
            while (j < dec.size() && dec.get(j).get(0) < fe) j++;
            // System.out.println("1. i: " + i + " j: " + j);
            if (j == dec.size() || dec.get(j).get(0) > fe) continue;
            // System.out.println("2. i: " + i + " j: " + j);
            List<Integer> decPart = dec.get(j);
            int ds = decPart.get(0), de = decPart.get(1);
            while (k < inc.size() && inc.get(k).get(0) < de) k++;
            if (k == inc.size() || inc.get(k).get(0) > de) continue;
            // System.out.println("3. i: " + i + " j: " + j + " k: " + k);
            List<Integer> lastInc = inc.get(k);
            int ls = lastInc.get(0), le = lastInc.get(1);
            long leftSum = nums[fe], leftMx = INF;
            for (int x = fe - 1; x >= fs; x--) {
                leftSum += nums[x];
                if (leftSum > leftMx) leftMx = leftSum;
            }
            long rightSum = nums[ls], rightMx = INF;
            for (int x = ls + 1; x <= le; x++) {
                rightSum += nums[x];
                if (rightSum > rightMx) rightMx = rightSum;
            }
            long midSum = prefix[de] - prefix[ds + 1];
            // System.out.println("3. fs: " + fs + " fe: " + fe + " ds: " + ds + " de: " + de + " ls: " + ls + " le: " + le);
            // System.out.println("3. leftSum: " + leftSum + " midSum: " + midSum + " rightSum: " + rightSum);
            // System.out.println("3. leftMx: " + leftMx + " midSum: " + midSum + " rightMx: " + rightMx);
            ret = Math.max(ret, leftMx + midSum + rightMx);
        }
        return ret;
    }
}
