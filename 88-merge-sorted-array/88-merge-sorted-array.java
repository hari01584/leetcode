/*
explanation: merge sorted array in-place with constant space complexity! the algorithm works by sorting the array from last and filling the right-most zeroes first! The loop is iterated backwards and bigger element of two lists are used!

testcase:
[1]
1
[]
0 -> Works

Time & Space Complexity: O(n) O(1)
Time complexity is O(n) since a single linear loop is required, While the space complexity is O(1) because in-place shifting is used.
*/

class Solution {
    public void swap(int a, int b, int[] nums){
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int a = m-1;
        int b = n-1;
        
        int zr = m + n - 1;
        int BIG_NEGAV = -9999999;
        while(zr>=0){
            int e1, e2;
            if(a < 0){
                e1 = BIG_NEGAV;
            }
            else{
                e1 = nums1[a];
            }
            
            if(b < 0){
                e2 = BIG_NEGAV;
            }
            else{
                e2 = nums2[b];
            }
            
            System.out.println(e1+" "+e2+" "+a+" "+b);
            if(e1 > e2){
                nums1[zr--] = e1;
                a--;
            }
            else{
                nums1[zr--] = e2;
                b--;
            }
        }
    }
}