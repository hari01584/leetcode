/*
explanation: piece of cake using piece of cake algorithm! the algorithm works by first sorting all the cuts location and finding the difference in cut locations! as you can observe that the maximum area enclosed will only depend upon the space between each consecutive cuts and therefore the maximum cut will be decided by greatest distance between each cuts! therefore this algortihm finds the maximum distance between differences of all the cuts both horizontally and vertically, and finally returns the value!

testcase: 
5
4
[1,2,4]
[1,3]
-> Works by first finding maximum spacing in cuts, here the max spacing in 1st array ([1, 2, 4]) is max(1-0, 2-1, 4-2, 5-4) = 2, similiarly for vertical, max(1-0, 3-1, 4-2) = 2, therefore maximum area becomes max(horizontal cut) * max(vertical cut) or 2*2 = 4! (module 10^9+7), please note that the end cases covering 0th cut (ie cut on pos 0 and pos max) is also considered, ie in horizontal 1-0 and 5-4 cut! these are to get margin cuts in cake!

Time & Space Complexity: O(n) and O(1): Since linear looping is used in both array therefore time complexity is O(n), also since constant number of variables are used there space complexity is O(1).
*/

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int i;
        Arrays.sort(horizontalCuts); 
        Arrays.sort(verticalCuts); 

        int hwc = horizontalCuts[0];
        for(i=1; i<horizontalCuts.length; i++){
            hwc = Math.max(hwc, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        hwc = Math.max(hwc, h - horizontalCuts[i-1]);
        
        int vwc = verticalCuts[0];
        for(i=1; i<verticalCuts.length; i++){
            vwc = Math.max(vwc, verticalCuts[i] - verticalCuts[i-1]);
        }
        vwc = Math.max(vwc, w - verticalCuts[i-1]);

        // System.out.println(hwc+" "+vwc);
        
        long val = hwc*(long)vwc; // Trick: convert to long datatype for big integer multiplications!
        val = val % 1000000007;
        return (int)val;
    }
}