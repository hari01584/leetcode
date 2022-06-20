/*
explanation: calculates the area by first building left and right subarray for just-barely-least and getting w = rl - ll - 1 and calculating total area. The program works on principles of dynamic programming where it step-by-step builds the position of bar just less than the current one and continues on.

testcase: [2, 4] -> Works

Time and Space Complexity: O(n) and O(n): Since building barely-least array takes 2 linear loops of time complexity O(n) and iterating over to find max area also takes the same time therefore the total time complexity is O(n), also since two more array of size n is stored to record the indices of left and right max parts therefore space complexity is also O(n)
*/

class Solution {
    public int getVal(int[] heights, int p){
        if(p==-1) return -1;
        else return heights[p];
    }
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1) return heights[0];
        
        int rightleast[] = new int[heights.length];
        int leftleast[] = new int[heights.length];

        rightleast[heights.length - 1] = -1;
        for(int i=heights.length-2; i>=0; i--){
            int p = i+1;
            while(heights[i] <= getVal(heights, p)){
                // System.out.println("Jumping from "+getVal(heights, p)+" to "+rightleast[p]);
                p = rightleast[p];
            }
            // System.out.println("Setting "+heights[i]+" limit to "+getVal(heights, p));
            rightleast[i] = p;
        }
        
        
        leftleast[0] = -1;
        for(int i=1; i<heights.length; i++){
            // Calc max area
            
            int p = i-1;
            while(heights[i] <= getVal(heights, p)){
                p = leftleast[p];
            }
            leftleast[i] = p;
        }
        
        // System.out.println(Arrays.toString(rightleast));
        // System.out.println(Arrays.toString(leftleast));

        
        int m = heights[0];
        for(int i=0; i<heights.length; i++){
            int rl, ll;
            if(rightleast[i] == -1) rl = heights.length;
            else rl = rightleast[i];

            if(leftleast[i] == -1) ll = -1;
            else ll = leftleast[i];

            int w = rl - ll - 1;
            m = Math.max(m, w*heights[i]);
            // System.out.println("h "+heights[i]+" width "+w);
        }
        
        return m;
    }
}