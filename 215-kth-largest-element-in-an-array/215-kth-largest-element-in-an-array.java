/*
explanation: finding kth largest element using quicksort! explained in https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60333/Concise-JAVA-solution-based-on-Quick-Select

testcase: [3,2,1,5,6,4], k = 2
works!

Time & Space Complexity: O(n^2)[worst] and O(1) in worst case.
*/
class Solution {
    public int partition(int[] nums, int low, int high){
        int line = low;
        int partition = nums[high];
        for(int i=low; i<=high; i++){
            if(nums[i] < partition){
                int temp = nums[i];
                nums[i] = nums[line];
                nums[line] = temp;
                line++;
            }
        }
        
        int temp = nums[high];
        nums[high] = nums[line];
        nums[line] = temp;
  
        return line;
    }
    
    public int quickSelect(int[] nums, int k, int start, int end){
        int index = partition(nums, start, end);
        if(index == k) return nums[index];
        else if(index < k){
            return quickSelect(nums, k, index+1, end);
        }
        else{
            return quickSelect(nums, k, start, index-1);
        }
    }
    
    public int findKthLargest(int[] nums, int k) {        
        int pinx = quickSelect(nums, nums.length - k, 0, nums.length-1);
        
        // System.out.println(pinx);
        // System.out.println(Arrays.toString(nums));

        return pinx;
    }
}