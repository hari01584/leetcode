/*
explanation: finds exactly once appearing element using binary sort, it uses the fact that if in binary search of even numbers, the singular element will break left and right symmetry, and hence using it can find the non duplicate.

testcase:
[3,3,7,7,11,11,12] -> Works

Time & Space Complexity: O(logn) and O(1)
*/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int e=-1;
        if(nums.length == 1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[end] != nums[end-1]) return nums[end];

        while(end > start){
            int m = (start + end) / 2;
            if(m%2!=0) m++;
            System.out.println("I "+start+" "+m+" "+end);
            e = nums[m];
            int nx = nums[m+1];
            int pv = nums[m-1];
            
            System.out.println(pv+" "+e+" "+nx);

            if(e != nx && e != pv) break;
            if(e != nx){
                end = m - 1;
            }
            else{
                start = m+1;
            }            
        }
        
        return e;
    }
}