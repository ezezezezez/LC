// 4. Median of Two Sorted Arrays
public class P4_2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, (total + 1) / 2);
        } else {
            return (findKth(nums1, 0, nums2, 0, total / 2) + findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
        }
    }

    int findKth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart >= A.length) return B[bStart + k - 1];
        else if (bStart >= B.length) return A[aStart + k - 1];
        else if (k == 1) return Math.min(A[aStart], B[bStart]);

        int pa = Math.min(k / 2, A.length - aStart);
        int pb = Math.min(k / 2, B.length - bStart);

        if (A[aStart + pa - 1] < B[bStart + pb - 1]) {
            return findKth(A, aStart + pa, B, bStart, k - pa);
        } else {
            return findKth(A, aStart, B, bStart + pb, k - pb);
        }
    }
}