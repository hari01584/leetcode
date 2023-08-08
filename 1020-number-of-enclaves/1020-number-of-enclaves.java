/*
explanation: number of enclaves using dfs and visited array algorithm, it works by visited each possible node connected
to falling off edge and checking what are all connected to it. The final answer is number of land nodes minus the number
of land nodes connected to cliff.

testcase: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] -> Works

Time & Space Complexity: O(nm) & O(nm): Time complexity is quadratic, ie n*m due to the visited of each element and dfs, wheareas space complexity is also the same due to the matrix required to store.
*/
class Solution {
    public void dfs(int i, int j, int[][] grid, int[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        
        // If this not good element to traverse
        int element = grid[i][j];
        if (element == 0)
            return;
        
        // Else if already visited then also return
        if (visited[i][j] == 1)
            return;
        
        visited[i][j] = 1;
        
        // Visit in 4 directions!
        dfs(i + 1, j, grid, visited);
        dfs(i - 1, j, grid, visited);
        dfs(i, j + 1, grid, visited);
        dfs(i, j - 1, grid, visited);
    }

    public int numEnclaves(int[][] grid) {
        // To solve this, use visited array and a dfs to quickly explore the edges
        // Created visited array
        int[][] visited = new int[grid.length][grid[0].length];
        
        // Top and bottom section!
        for (int i=0; i<grid[0].length; i++) {
            if (grid[0][i] == 1) {
                dfs(0, i, grid, visited);
            }
            
            if (grid[grid.length - 1][i] == 1) {
                dfs(grid.length - 1, i, grid, visited);
            }
        }
        
        // Left and right section!
        for (int j=0; j<grid.length; j++) {
            if (grid[j][0] == 1) {
                dfs(j, 0, grid, visited);
            }
            
            if (grid[j][grid[0].length - 1] == 1) {
                dfs(j, grid[0].length - 1, grid, visited);
            }
        }
        
        int total_number_of_lands = 0;
        int land_that_can_fall = 0;
        
        // Now calculate how many cells were visited!, these visited would be the elements from wherre we can jump off
        // subtract this from tootal number of ones!
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    total_number_of_lands++;
                    // Check if this 1 is visited or not!
                    if (visited[i][j] == 1) {
                        land_that_can_fall++;
                    }
                }
            }
        }
        
        return total_number_of_lands - land_that_can_fall;
    }
}