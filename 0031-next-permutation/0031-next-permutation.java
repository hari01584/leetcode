/*
explanation: find next permutation using swapping digits! the algorithm works by finding strictly greater digit
at each position of array (when iterating backwards), if found then it choose to swap these two digits and sort
remaining elements in array!

testcase: [1,2,3] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to single iteration, time complexity is constant due
to countable variables used.
*/
class Solution {
    public void nextPermutation(int[] nums) {
        TreeMap<Integer, Integer> mymap = new TreeMap<>();
        Map.Entry<Integer, Integer> entry;

        // Where breaking happens
        int pivot_index = -1;
        for(int i=nums.length-1; i>=0; i--) {
            // For each element, check if there's a larger one in right
            int current_digit = nums[i];
            
            // Get higher than this
            entry = mymap.higherEntry(current_digit);

            if (entry != null) {
                // Found a place, we set this
                pivot_index = i;

                // Swap elements!
                int index = entry.getValue();
                
                // System.out.println("Swapping "+pivot_index+" and "+index);
                nums[pivot_index] ^= nums[index];
                nums[index] ^= nums[pivot_index];
                nums[pivot_index] ^= nums[index];
                
                // Finally break for further sorting!
                break;
            }
            
            mymap.put(current_digit, i);
        }
        
        // Sort remaining elements!
        Arrays.sort(nums, pivot_index + 1, nums.length);
    }
}