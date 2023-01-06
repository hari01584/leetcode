/*
explanation: find max ice bar by greedy approach! The program works by sorting the cost in ascending order and step by step simulating the purchase of ice cream bar if we have enough coins! The program ends if coins < next bar cost and answer is returned.

testcase: [1,3,2,4,1], 7 -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity is nlogn due to the sorting needed, also space complexity is constant due to the countable variable required throughout the program.
*/
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ans = 0;
        for(Integer e : costs){
            if(coins >= e){
                ans++;
                coins -= e;
            } else {
                break;
            }
        }
        
        return ans;
    }
}