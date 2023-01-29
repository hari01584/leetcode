/*
explanation: put marbles using greedy sort algorithm! The program works by using weights array and sorting them accordingly the logic as described in https://leetcode.com/problems/put-marbles-in-bags/discuss/3111642/Simple-C%2B%2B-solution-using-sort.-Idea-Explained.
*/
class Solution {
    public long putMarbles(int[] weights, int k) {
        if(weights.length == k || k==1) return 0;
        
        for(int i=0; i<weights.length-1; i++){
            weights[i] = weights[i] + weights[i+1];
        }
        
        weights[weights.length-1] = Integer.MAX_VALUE;
        
        Arrays.sort(weights);
        long diff = 0;
        for(int i=0; i<k-1; i++){
            int l = weights[i];
            int r = weights[weights.length-i-2];
            diff += r-l;
        }
        
        return diff;
    }
}