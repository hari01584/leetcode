/*
explanation: minimum falling path sum using dynamic programming! The programs works by cumulatively calculating cost to reach each element in grid! The algorithm works by finding path to reach specific node by taking minima of cost reaching the previous (upper layer path)!

testcase: [[2,1,3],[6,5,4],[7,8,9]] -> Works
here the algorithm modifies this into cost matrix, initially the loop will run from 2nd row (6, 5, 4) where the cost of reaching 6 will be 6 + min(cost(2), cost(1), cost(3)), which will turn out to be 6+1 (or 7), similiarly we do same with each element in columns and subsequently all rows. at the end the program will iterate through last row in matrix and return the minimum value!

Time & Space Complexity: O(n) & O(1): Time complexity of this program is linear due to requirement of looping through each element in matrix, also space complexity is constant due to inplace replacement of values in matrix!
*/
class Solution {
    int[][] m;
    
    public int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
    
    public int getVal(int x, int y){
        if(y<0 || y>=m[0].length) return Integer.MAX_VALUE;
        return m[x][y];
    }
    
    public int minFallingPathSum(int[][] matrix) {
        m = matrix;
        for(int row=1; row<matrix.length; row++){
            for(int col=0; col<matrix[row].length; col++){
                matrix[row][col] += min(getVal(row-1, col-1), getVal(row-1, col), getVal(row-1, col+1));
            }
        }
        
        int ans = Integer.MAX_VALUE;
        int[] lstrow = matrix[matrix.length-1];
        for(int i=0; i<lstrow.length; i++){
            ans = Math.min(ans, lstrow[i]);
        }
        return ans;
    }
}