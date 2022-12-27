class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] lis = new int[nums.length];
        
        int largest = 1;
        for(int i=nums.length-1; i>=0; i--){
            int e = nums[i];
            int lng = 1;
            for(int j=i+1; j<nums.length; j++){
                if(nums[j] > nums[i]){
                    lng = Math.max(lng, 1+lis[j]);
                }
            }
            lis[i] = lng;
            largest = Math.max(largest, lng);
        }
        
        return largest;
    }
}