/*
explanation: check if sorted and rotated using 2-pass approach, the program works by first checking sort condition normally ie a good sorted non decreasing array (without rotation) will always have it's current_element >= previous_element! Please note how it's called non-decreasing but not increasing array, there is slight difference, for increasing array elements must always be greater than previous ones (ie always increase), whereas for non-decreasing, current element could be equal to previous element or greater than than (ie it can be increasing, or equal, but never decreasing)..

So for the first time a bad element is found, we use it as pivot and check again taking that element as starting point and circularly checking all items next in array, if found good (satisfies non-decreasing condition) then good and return true

testcase: [3,4,5,1,2] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to iterations required over each element, also space complexity is constant due to countable variables used.
*/
class Solution {
    public boolean check(int[] nums) {
        // Two pass approach, we go from start to end try to find pivot then check from that loc
        int pivot_point = -1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                // Found our pivot!
                pivot_point = i;
            }
        }
        
        if (pivot_point == -1) {
            // If not key was found, then this array is already good (ie without rotation and sorted)
            return true;
        }
        
        // If found, we additionally check if starting from pivot_point index to pivot_point + length, do we get any bad element
        for (int i=pivot_point + 1; i < pivot_point + nums.length; i++) {
            int current_element = nums[i % nums.length];
            int prev_element = nums[(i-1) % nums.length];
            if (current_element < prev_element) {
                return false;
            }
        }
        
        // If everything good then this is sorted + rotated array!
        return true;
    }
}