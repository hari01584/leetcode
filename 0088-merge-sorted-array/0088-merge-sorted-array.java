/*
explanation: merged sorted array in constant space using iterative algorithm, the program works by taking both arrays
and inserting maximum elements from the two in revers order.

testcase:
[1,2,3,0,0,0] 3 -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to single pass iteration required, space complexity
is constant due to countable extra variables
*/
class Solution {
    public int getOrReturn(int[] nums, int index) {
        if (index < 0 || index >= nums.length) return Integer.MIN_VALUE;
        return nums[index];
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Convert length to index of last element
        m--;
        n--;
        int p = nums1.length - 1;
        while (m >= 0 || n >= 0) {
            int m_value = getOrReturn(nums1, m);
            int n_value = getOrReturn(nums2, n);
            
            if (m_value > n_value) {
                // Put this to last
                nums1[p--] = m_value;
                m--;
            } else {
                nums1[p--] = n_value;
                n--;
            }
        }
    }
}