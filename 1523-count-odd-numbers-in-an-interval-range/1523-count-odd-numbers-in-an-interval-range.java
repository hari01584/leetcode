/*
explanation: count odd numbers by loop! Program works by using counter to add to answer for each odd number!

testcase: 3 7 -> Works

Time & Space Complexity: O(n) & O(1): Time and space complexity is linear due to linear loops required, space complexity constant due to countable variables!
*/
class Solution {
    public int countOdds(int low, int high) {
        int ans = 0;
        if(low%2 == 0) low++;
        for(int i=low; i<=high; i=i+2){
            ans++;
        }
        return ans;
    }
}