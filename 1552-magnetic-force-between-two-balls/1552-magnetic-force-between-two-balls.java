/*
explanation: distributing balls by using binary search! the algorithm works by first defining a function which calculates number of balls that can be placed for atleast m value of magnetic force between any two balls, using simulation approach it finds the number of possible balls. Now after defining function we simply run a binary search over the min..max interval of position array to adjust bounds until the limiting condition of balls are reached.

testcase:
[1,2,3,4,7]
3
Here, first the mid is 4, ie number of balls in basked for atleast 4 minimum magnetic distance is 2, this gives false from our isSafe function and bound shifts to left which is 2, now this value 2 gives ball as 3 which gives true and our bound shifts to right ie 3, now at this step the ball turn out to be 3 and the loop also ends since start>end, therefore value is returned.

Time & Space Complexity: O(nlogn) & O(1): Since arrays sorting is used which takes tc of nlogn and also since constant number of variables are defined and used therefore space complexity is O(1).
*/

class Solution {
    public boolean isSafe(int[] arr, int m, int fit){
        // Find number of balls for atleast fit magnetic force between each balls!
        
        // Simulation approach! Place first ball at first position
        int sum = 0;
        int dfx = 0;
        int balls = 1;
        for(int i=1; i<arr.length; i++){
            dfx = arr[i] - arr[i-1];
            if(sum + dfx > fit){
                sum = 0;
                balls++;
            }
            else{
                sum += dfx;
            }
        }
        
        System.out.println("Balls "+balls);
        if(balls < m) return false;
        
        return true;
    }
    public int maxDistance(int[] position, int m) {
        int l = position.length;
        
        int start=position[0];
        int end=0;
        
        Arrays.sort(position);
        // for(int i=0; i<l; i++){
        //     start = Math.min(start, position[i]);
        //     end = Math.max(end, position[i]);
        // }
        start = 0;
        end = position[position.length-1];
        
        int soln = -1;
        while(start <= end){
            int mid = (start+end)/2;
            System.out.println(" "+mid);
            if(isSafe(position, m, mid)){
                start = mid + 1;
            }
            else{
                soln = mid;
                end = mid - 1;
            }
        }
        
        
        return soln;
    }
}