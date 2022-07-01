/*
explanation: capacity finder by using binary searching algorithm! it works by first defining a function daysWithMaxCap which calculates the number of days required to ship all items with each ship having capacity no more than cap! using it we get different days and running a binary search with start=maximum of weights and end=sum of all weights, gives us the days required (by changing capacity and probing the days required for each capacity)!

testcase:
1,2,3,4,5,6,7,8,9,10
5
-> Works

Time & Space Complexity: O(nlogn) and O(1): Since daysWithMaxCap has time complexity as O(n) which is called log(n) times under binary search loop therefore total time complexity is O(nlogn), also because countable variables are used throughout the program therefore space complexity is O(1)!
*/

class Solution {
    public int daysWithMaxCap(int[] weights, int cap){
        int days = 0;
        int wt = 0;
        for(int i=0; i<weights.length; i++){
            int w = weights[i];
            if(wt + w > cap){
                days++;
                wt = w;
            }
            else{
                wt += w;
            }
        }
        return days;
    }
    public int shipWithinDays(int[] weights, int days) {
        int start = weights[0];
        int end = weights[0];
        for(int i=1; i<weights.length; i++){
            start = Math.max(start, weights[i]);
            end += weights[i];
        }
        
        int soln = -1;
        while(start<=end){
            int mid = (start+end)/2;
            int d = daysWithMaxCap(weights, mid);
            // System.out.println(start+" "+end+" "+mid+" "+d);
            if(d < days){
                soln = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
                
        return soln;
    }
}