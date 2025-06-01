// 4. Median of Two Sorted Arrays
// This solution finds the median of two sorted arrays using binary search on the smaller array.
public class P4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to minimize binary search steps.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length; // Length of the first (smaller) array.
        int n = nums2.length; // Length of the second array.
        int lo = 0, hi = m;   // Binary search boundaries for nums1.

        // These variables will store the candidates for the median elements.
        int median1 = 0, median2 = 0;

        // Perform binary search in nums1 to find the perfect partition.
        while (lo <= hi) {
            // Partition index in nums1.
            int midI = (lo + hi) >> 1;
            // Partition index in nums2, ensuring that combined left partitions have half the total elements.
            int midJ = (m + n + 1) / 2 - midI;

            // Set left boundary for nums1:
            // If midI is 0, then there are no elements on the left side of nums1,
            // so we use Integer.MIN_VALUE as a placeholder.
            int prevNumI = midI == 0 ? Integer.MIN_VALUE : nums1[midI - 1];

            // Set right boundary for nums1:
            // If midI has reached the end of nums1, use Integer.MAX_VALUE so it doesn't interfere.
            int numI = midI == m ? Integer.MAX_VALUE : nums1[midI];

            // Set left boundary for nums2 similarly:
            int prevNumJ = midJ == 0 ? Integer.MIN_VALUE : nums2[midJ - 1];

            // Set right boundary for nums2 similarly:
            int numJ = midJ == n ? Integer.MAX_VALUE : nums2[midJ];

            // Check if we have a valid partition:
            // The condition numJ >= prevNumI ensures that the largest item in the left partition of nums1
            // does not exceed the smallest item in the right partition of nums2.
            if (numJ >= prevNumI) {
                // Move the lower bound right to try to push the partition further on nums1.
                lo = midI + 1;
                // median1 is the maximum element from the left partitions of both arrays.
                median1 = Math.max(prevNumI, prevNumJ);
                // median2 is the minimum element from the right partitions of both arrays.
                median2 = Math.min(numI, numJ);
            } else {
                // The partition in nums1 is too far right.
                // Adjust by moving the high pointer to the left.
                hi = midI - 1;
            }
        }

        // If the total number of elements is even, the median is the average of median1 and median2.
        // Otherwise, it is simply median1, as the left partition will have one extra element.
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}