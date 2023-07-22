/*
explanation: 4Sum using n-ary sum algorithm! The below program implements a generalized version of 2-Sum, 3-Sum,
4-Sum... N-ary Sum using recursion! It works by first fixing an element and then picking it and going to next element
to find the remaining sum, it does this until only two elements sum is required for which the algorithm switches to
binary technique to find all possible combinations of sum!

testcase: [1,0,-1,0,-2,2] -> Works

Time & Space Complexity: O(n^(t-1)) & O(n^(t-1)): Time complexity is n to the power of factor n-2, for this when we are finding
4 sum (ie t=4), the time complexity is simply O(n^3) also, the space complexity is same for storing lists and answers!
*/
class Solution {
    List<List<Integer>> binaryAddition(int start, int end, long target, int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int left = start;
        int right = end;
        
        while (left < right) {
            long sum = nums[left] + nums[right];
            if (sum == target) {
                // Good element, add to our ans list!
                List<Integer> l = new ArrayList<>();
                l.add(nums[left]);
                l.add(nums[right]);
                ans.add(l);
                
                // For next element, increase left by 1 and make sure we are not taking
                // duplicates for it ie
                while (++left <= end && nums[left] == nums[left - 1]);
                
                // Similiarly do for right as well
                while (--right >= 0 && nums[right] == nums[right + 1]);
            }
            else if (sum < target) {
                // Increase left pointer, ie increase overall value
                left++;
            } else {
                right--;
            }
        }
        
        return ans;
    }

    // Requirement, run only for sorted arrays
    List<List<Integer>> nrecursum(int start, int end, long target, int t, int[] nums) {
        if (start < 0 || end >= nums.length || start >= end)
            return new ArrayList<>();

        if (t == 2) {
            // If n is 2, then apply the binary addition technique to get all two tuplets for addition = target
            return binaryAddition(start, end, target, nums);
        }

        // List to save all remaining items
        List<List<Integer>> arr = new ArrayList<>();
        // Fix a pointer to index and apply algorithm to next step
        int p = start;
        while (p <= end) {
            // Assuming this point is now fixed, therefore this element is
            int element = nums[p];
            // Target remaining to be fulfilled after taking this number into account will be:
            long mytarget = target - element;
            // Find all the lists that can satisfy remaining target and append this element as well!
            List<List<Integer>> nextargets = nrecursum(p + 1, end, mytarget, t - 1, nums);
            // Get all those lists, and add my element as well
            for (int i=0; i<nextargets.size(); i++) {
                List<Integer> mylist = nextargets.get(i);
                mylist.add(element);
                
                // Now add that list to our final adding list
                arr.add(mylist);
            }
            
            p++;
            // Also, if this element is same as previous one, just fast forward our list
            while (p <= end && nums[p] == nums[p-1]) p++;
        }
        
        return arr;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        
        return nrecursum(0, nums.length-1, target, 4, nums);
    }
}