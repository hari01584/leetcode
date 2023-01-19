/*
explanation: subarray sums divisible by k! the program works on principle of using prefix sum array to find sum between any two indices i, j using sum[i, j] = sum[0, i] - sum[0, j], using this property we can find the sum and check divisibility properties at each point.
also - https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217980/Java-O(N)-with-HashMap-and-prefix-Sum for linear optimization

testcase: [4,5,0,-2,-3,1]
5 -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to use of one loops, also space complexity is constant due to inplace editing of nums array and use of countable variables in program!
*/
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        
        HashMap<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        for(int i=0; i<nums.length; i++){
            if(i != 0) nums[i] = nums[i-1] + nums[i];
            // System.out.println("Currently in consider: "+nums[i]);
            
            int r = nums[i] % k;
            if(r < 0) r+=k;
            int init = freq.getOrDefault(r, 0);
            count += init;
            freq.put(r, init+1);
        }
        // System.out.println(Arrays.toString(nums));
        return count;
    }
}