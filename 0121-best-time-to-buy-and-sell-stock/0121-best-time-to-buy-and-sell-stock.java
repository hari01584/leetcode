/*
explanation: finding best time by converting prices array to the change in prices of each days and then using Kadane's algorithm to find maximum sum contigous subarray!

testcase:
[7,1,5,3,6,4]
first converting it to price difference array we get -> [0, -6, +4, -2, +3, -2], now applying kadanes theorem we can get max subarray to be +4, -2, +3 with max sum = 5

Time & Space Complexity: O(n) and O(1)
Since linear looping over prices array of size n is needed, the time complexity is O(n). Also because we are only storing few extra variables in algorithm therefore space complexity is O(1).
*/

class Solution {
    public int maxProfit(int[] prices) {
        int globalMax = 0;
        int max = 0;
        for(int i=prices.length-1; i>=1; i--){
            prices[i] = prices[i] - prices[i-1];
        }
        prices[0] = 0;
        
        for(int i=0; i<prices.length; i++){
            int p = prices[i];
            max = Math.max(p, p + max);
            globalMax = Math.max(globalMax, max);
        }
        
        System.out.println(max+" "+globalMax);
        
        return globalMax;
    }
}