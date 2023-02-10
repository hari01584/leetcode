/*
explanation: as far as land using 2d scanning! The program works by first scanning the tree to get cost from top and left elements, then we iterate in reverse order and get cost from bottom and right, doing this finally we return the maximum value in elements array!

testcase: [[1,0,1],[0,0,0],[1,0,1]] -> Works

Time & Space Complexity: O(n^2) & O(n^2): Time complexity is n square due to looping over elements and the space required to store!
*/
class Solution {
    int[][] grid;
    
    boolean outbounds(int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return true;
        return false;
    }
    
    int item(int i, int j, int[][] arr){
        if(outbounds(i, j)) return 1000000; // Overflow adjustment
        return arr[i][j];
    }

    
    public int maxDistance(int[][] grid) {
        this.grid = grid;
        
        boolean is_zero = false;
        boolean is_one = false;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 0) is_zero = true;
                if(grid[i][j] == 1) is_one = true;
            }
        }
        if(!is_zero || !is_one) return -1; // Exception cases

        // Top left to bottom right scan!
        int[][] topleft = new int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1) topleft[i][j] = 0;
                else {
                    topleft[i][j] = 1 + Math.min(item(i-1, j, topleft), item(i, j-1, topleft));
                }
            }
        }
        
        int ans = Integer.MIN_VALUE;
        
        // Bottom right to top scan
        for(int i=grid.length-1; i>=0; i--){
            for(int j=grid[0].length-1; j>=0; j--){
                if(grid[i][j] == 1) topleft[i][j] = 0;
                else {
                    int nw = 1 + Math.min(item(i+1, j, topleft), item(i, j+1, topleft));
                    topleft[i][j] = Math.min(topleft[i][j], nw);
                }
                ans = Math.max(ans, topleft[i][j]);
            }
        }
        
        // System.out.println(Arrays.deepToString(topleft).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        
        if(ans == Integer.MIN_VALUE) return -1;
        return ans;
    }
}