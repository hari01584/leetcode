/*
explanation: in-place sorting colors by maintaining a hashmap-like arrays containing count of each color red, blue, white. The colors are then added back to nums array and hence with extra space and linear ordering the array is sorted!

testcase:
[2,0,2,1,1,0] -> count list will become [2, 2, 2], and thus final output will be 0 0 1 1 2 2

Time and Space Complexity: O(n) and O(1)
Since only once is the array iterated, the time complexity is O(n), and since countable extra variables are defined and used therefore Space Complexity is O(1).
*/

class Solution {
    public void sortColors(int[] nums) {
        int l = nums.length;
        int[] count = new int[3];
        count[0] = 0;
        count[1] = 0;
        count[2] = 0;
        
        for(int i=0; i<l; i++){
            int n = nums[i];
            count[n]++;
        }
        
        int p = 0;
        for(int i=0; i<count[0]; i++){
            nums[p++] = 0;
        }
        for(int i=0; i<count[1]; i++){
            nums[p++] = 1;
        }
        for(int i=0; i<count[2]; i++){
            nums[p++] = 2;
        }
    }
}