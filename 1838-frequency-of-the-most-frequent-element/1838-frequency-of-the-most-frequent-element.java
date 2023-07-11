/*
explanation: frequency find using principles of sliding window and sort! more explanations: https://leetcode.com/problems/frequency-of-the-most-frequent-element/discuss/1370353/Easy-to-understand-for-beginners-as-well(approach-explained)
*/
class Solution {
    public int maxFrequency(int[] nums, int k) {
        int l = 0;
        int r = 0;
        
        int max = -1;
        int sum = 0;
        
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        while (r < nums.length) {
            sum += nums[r];
            
            // Check cost
            int cost = (nums[r] * (r - l + 1)) - sum;
            while (cost > k) {
                // Bad, rewind window now!
                sum -= nums[l];
                l++;
                cost = (r * (r - l + 1)) - sum;
            }
                       
            max = Math.max(max, r - l + 1);
            // Add in right element, ie expand automatically
            r++;
        }
        
        return max;
    }
}