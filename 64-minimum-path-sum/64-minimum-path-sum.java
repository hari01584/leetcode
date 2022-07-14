/*
explanation: min path sum by maintaining a dp table which records the minimum distance required to reach the given cell! Which is equal to minimum of distance required to reach top or left cell! As we can see this is optimal substructure problem so maintaining this dp table and returning the last element will give our answer!

testcase:
[[1,3,1],[1,5,1],[4,2,1]] -> Works
here the final matrix after all the calculations will become:
[1, 4, 5]
[2, 7, 6]
[6, 8, 7]

As you can see, each element in the 2d array is the aggregate sum of minimum of top, left element  with its own cost!

Time & Space Complexity: O(n^2) & O(1): Since we use a nested loop of level 2 therefore the time complexity of program is O(n^2), also since all the changes done is in matrix itself (we are not allocating seperate space for new matrix) therefore space complexity is O(1)
*/

class Solution {
    public int getElement(int[][] grid, int i, int j){
        if(i<0 || j<0) return 10000000;
        else return grid[i][j];
    }
    public int minPathSum(int[][] grid) {
        int i=0;
        int j=0;
        for(i=0; i<grid.length; i++){
            for(j=0; j<grid[0].length; j++){
                if(i==0 && j==0) continue;
                
                int top = getElement(grid, i-1, j);
                int left = getElement(grid, i, j-1);
                grid[i][j] = grid[i][j] + Math.min(top, left);
            }
        }
        
        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        return grid[i-1][j-1];
    }
}