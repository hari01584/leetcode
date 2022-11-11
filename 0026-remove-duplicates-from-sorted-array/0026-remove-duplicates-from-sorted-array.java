/*
explanation: remove duplicates from sorted array by two pointers approach, the program works by having two pointers, one which iterates over the elements iteratively and second pointer which sets the value of item when non duplicates are found! See testcase for dry run.

testcase: [0,0,1,1,1,2,2,3,3,4] -> Works
in this program let us have our pointer i, j. both set to 0 first, now initially we check if value of nums[0] == nums[0], which is true therefore we skip over this element and now for next iteration i=1, now the same happens and we reach to i=2! Now for the element 1 is not equal to 0, therefore we set the next element of pointer p=1 to 1, therefore the array becomes [0,1,1,1.....] and i becomes 3 and p becomes 1, for the next iteration i=4 and p=2, now this goes to i=5, where since 2!=1 we set the element at p=3 to be 2 and loop goes on, finally the answer becomes [0,1,2,3,4]

Time & Space Complexity: Time complexity of this program is linear since we have to iterate through the loop only once, also the space complexity is constant since we only maintain two constant pointers for whole program.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int repoint = 0;
        int itpoint = 0;
        
        int p = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == nums[p]){
                // Dont iterate no same
            } else {
                nums[++p] = nums[i];
            }
        }
        
        return p+1;
    }
}