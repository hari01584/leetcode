/*
explanation: remove element using 2 pointers approach, the program works by using pointer i and j to shift bad elements (ie val) to end of array using swapping! iterating over each element and removing bad elements, we finally get to our result!

testcase:
[3,2,2,3]
3 -> Works

Time & Space Complexity: O(n) & O(1): Time and space complexity of this program is linear and constant respectively, this is because the program will need to loop through all n elements in all cases  (to check for pointers and bad/good values!), also space complexity is constant due to in-place editing of array contents and countable number of variables that is being used in the program.
*/
class Solution {
    
    public int removeElement(int[] nums, int val) {
        int i=0;
        int j=nums.length-1;
        for(i=0; i<nums.length; i++){
            while(j >= 0 && nums[j] == val){
                j--;
            }
            if(i >= j) break;

            if(nums[i] == val){
                // swap this with j
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
                j--;
            }
        }
        // System.out.println(i + " " + j);
        // System.out.println(Arrays.toString(nums));
        
        return j+1;
    }
}