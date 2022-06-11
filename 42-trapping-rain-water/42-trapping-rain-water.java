/*
explanation: approach 4 from solution https://leetcode.com/problems/trapping-rain-water/solution/
*/


class Solution {
    public int trap(int[] height) {
        int l = height.length;
        
        int leftMax = height[0];
        int rightMax = height[l-1];
        int left = 0;
        int right = l - 1;
        
        int points = 0;
        while(left < right){
            if(height[left] < height[right]){
                int p = leftMax - height[left];
                if(p > 0) points += p;
                leftMax = Math.max(leftMax, height[left]);
                left++;
                
                System.out.println("L " + left + " " + leftMax + " " + p);
            }
            else{
                int p = rightMax - height[right];
                if(p > 0) points += p;
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
            System.out.println(left + " " + right);
        }
        
        return points;
    }
}