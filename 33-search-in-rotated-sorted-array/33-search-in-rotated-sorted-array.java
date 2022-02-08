/*
explanation: searching in rotated sorted array using bineary search and O(log(n)) time complexity, the program works by first identifying the k (unknown rotated index) and uses simple binary searching function to search for values left and right subarray and returns the found index

testcase:
[1] 
1
-> Works, simple bsearch

[1] 
0
-> Works, gives -1 since bsearch can't find index

Time and Space Complexity: Time complexity is O(n), since the time reuired to search for unknown k need to iterate over all array to find it, hence O(n), Space complexity is O(log(n)) since recursive searching might make at max log(n) number of frames in stacks.
*/

class Solution {
    public int binarySearch(int start, int end, int target, int[] arr){
        int mid = (start + end)/2;
        if(arr[mid] == target){
            return mid;
        }
        if(mid == start) return -1;
        else if(arr[mid] < target){
            return binarySearch(mid, end, target, arr);
        }
        else{
            return binarySearch(start, mid, target, arr);
        }
    }
    
    public int search(int[] nums, int target) {
        int k = -1;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i] > nums[i+1]){
                k = i;
                break;
            }
        }
        // System.out.println(k);
        
        int r = -1;
        if(k != -1){
            int i = binarySearch(0, k+1, target, nums);
            // System.out.println(i);
            int j = binarySearch(k, nums.length, target, nums);
            // System.out.println(j);
            if(i==-1 && j!=-1) r = j;
            else if(i!=-1 && j==-1) r = i;
        }
        else{
            r = binarySearch(0, nums.length, target, nums);
        }
        return r;
    }
}