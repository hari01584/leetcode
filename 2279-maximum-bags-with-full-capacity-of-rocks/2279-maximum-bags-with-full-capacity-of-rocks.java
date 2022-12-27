/*
explanation: maximum bags using greedy approach! The algorithm works by first calculating rocks required by each bag (by substracting rocks from capacity), now this array is sorted to find the most easily possible fillable bags, (because no matter how many stones we have, it will always be easier to fill bags that are about to be filled, rather than investing stones in empty bags!), now using the simulation approach we fill rocks into this sorted requirement arrays and count until additionalRocks are over, the index where our rocks were < 0 will be our answer!

testcase:
[2,3,4,5]
[1,2,4,4]
2 -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity of this program is nlogn due to the sorting operations required in array, also space complexity is constant due to countable number of variables defined throughout the algorithm!
*/
class Solution {
    public int bSearch(int[] arr, int k){
        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int mid = (start+end)/2;
            if(k < arr[mid]){
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        for(int i=0; i<rocks.length; i++){
            capacity[i] -= rocks[i];
        }
        
        Arrays.sort(capacity);
        int k;
        for(k=0; k<rocks.length; k++){
            int required = capacity[k];
            if(additionalRocks >= required){
                additionalRocks -= required; // Fille rocks!
            } else {
                break;
            }
        }
        
        return k;
    }
}