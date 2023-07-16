/*
explanation: through reverse array trick defined here https://leetcode.com/problems/rotate-array/discuss/3758175/JAVA-0ms-100-faster-solution
*/
class Solution {
    int[] arr;
    
    void reverse(int start, int end) {
        while (start <= end) {
            // Swap
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            start++;
            end--;
        }
    }
    
    public void rotate(int[] nums, int k) {
        k %= nums.length;

        arr = nums;
        reverse(0, nums.length - 1 - k);
        reverse(nums.length - k, nums.length-1);
        reverse(0, nums.length-1);
    }
}