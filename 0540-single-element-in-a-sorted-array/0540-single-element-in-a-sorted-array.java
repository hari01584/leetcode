class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        if(nums.length == 1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[end] != nums[end-1]) return nums[end];

        while(start <= end){
            int mid = (int)Math.ceil((start+end)/2.0);
            if(mid%2!=0) mid++;
            
            if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) return nums[mid];
            
            if(nums[mid] == nums[mid+1]){
                // go forward!
                start = mid+1;
            } else {
                end = mid - 1;
            }
            
            // System.out.println("With "+mid+" start: "+start+", end: "+end);
        }
        
        return nums[end];
    }
}