import java.io.*;
import java.lang.*;
import java.util.*;

// 1352. Product of the Last K Numbers

public class P1358 {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int ret = 0;
        List<Integer> aIds = new ArrayList<>();
        List<Integer> bIds = new ArrayList<>();
        List<Integer> cIds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') aIds.add(i);
            else if (c == 'b') bIds.add(i);
            else cIds.add(i);
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                int bIdx = upperBound(bIds, 0, bIds.size(), i);
                int cIdx = upperBound(cIds, 0, cIds.size(), i);
                if (bIdx == bIds.size() || cIdx == cIds.size()) break;
                bIdx = bIds.get(bIdx);
                cIdx = cIds.get(cIdx);
                ret += n - Math.max(bIdx, cIdx);
            } else if (c == 'b') {
                int aIdx = upperBound(aIds, 0, aIds.size(), i);
                int cIdx = upperBound(cIds, 0, cIds.size(), i);
                if (aIdx == aIds.size() || cIdx == cIds.size()) break;
                aIdx = aIds.get(aIdx);
                cIdx = cIds.get(cIdx);
                ret += n - Math.max(aIdx, cIdx);
            } else {
                int aIdx = upperBound(aIds, 0, aIds.size(), i);
                int bIdx = upperBound(bIds, 0, bIds.size(), i);
                if (aIdx == aIds.size() || bIdx == bIds.size()) break;
                aIdx = aIds.get(aIdx);
                bIdx = bIds.get(bIdx);
                ret += n - Math.max(aIdx, bIdx);
            }
        }

        return ret;
    }

    int upperBound(List<Integer> nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums.get(mid) > val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    // O(n)
    public int numberOfSubstrings2(String s) {
        int n = s.length();
        int ret = 0;
        int[] cnt = new int[3];

        for (int i = 0, j = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                ret += n - i;
                cnt[s.charAt(j) - 'a']--;
                j++;
            }
        }

        return ret;
    }
}
