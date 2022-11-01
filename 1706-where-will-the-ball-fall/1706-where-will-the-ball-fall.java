/*
explanation: balldrop problem solved using simulation approach and dynamic programming! The program works by first simply finding the sitatuons where ball will block or not, if ball drops then we use the same recursion on next position. now for the ball to drop without problems, there are only two conditions:
1. If current cell orientation is left, then the left to this cell must also have same orientation, same for the right side.
2. For the prev or next cell based upon orientation, it must not be out of bounds.
for example, the case where point 1 fails is blue and purple ball in diagram, here at level 0 (first drop) since right of right oriented cell (down to blue) is opposite to it, therefore there is blockage, hence returned -1
for example 2, we can find a simple cell where ball is restricted between one of the yellow lines and the side black walls (not in diagram, have to imagine). Now using the parameters level and horizontal cell distance (h), we can solve the problem!!

testcase:
[[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]] -> Works
It simply works step by step in recursion, let's visualize the 1st red ball, what happens is at first recursive call (with level=0, h=0), we check the orientation of cell, it is right (1), thf. we check cell next to it for orientation, (which is also 1), since orientation is same therefore the ball can pass here successfully and we change our level=1 and h to be 0+1 (since ball is now dropped right, so next cell is 1, 1), we simply apply same recursion with parameters 1, 1 and check all conditions again, here 1,1 has orientation right again and the cell next to it is same, so new paramters become level=2, h=(1+1)=>2, for next cell 3,3 we see orientation left, now the cell left to this also have same orientation so no blockage and ball flows successfully. This simply works till either we reach one of the halting conditions where we return -1 or ball gets to level=5, where we return the horizontal value as final drop position! This all is cached to an array named dp where we see if any of previous ball have gone from this path, if yes then simply return that value or else apply algorithm!

Time & Space Complexity: O(n^2) and O(n^2): Worst Case Time  complexity is quadratic, in case where dp fails and each ball drop has to go through unique paths! Here each path may need row times iterations, and for column times ball drop the complexity becomes n^2, also space complexity is also quadratic since a 2d array dp is maintained at all times!
*/
class Solution {
    int[][] grid;
    int[][] dp;
    
    public boolean inBounds(int h){
        if(h < 0 || h >= grid[0].length) return false;
        return true;
    }
    
    public int ballDrop(int level, int h){
        if(level == grid.length) return h;
        
        // Dp use here (fetch)
        if(dp[level][h] != -2) return dp[level][h];
        
        // Out of bounds, box, so stuck
        if(!inBounds(h)) return dp[level][h] = -1;
        
        // Check orientation of current box, if orientation left then the left to this box must have same orientation
        // same for right box, if orientation is right then right of this box must also have same orientation (to have flow)
        int orient = grid[level][h];
        if(inBounds(h+orient) && grid[level][h+orient] == orient){
            // Pass, the ball can fall, hence return ballDrop of next fallen position
            return dp[level][h] = ballDrop(level+1, h+orient);
        } else {
            // Blockage, return -1
            return dp[level][h] = -1;
        }
    }
    
    public int[] findBall(int[][] grid) {
        this.grid = grid;
        int[] ans = new int[grid[0].length];
        
        dp = new int[grid.length][grid[0].length];
        for(int[] d:dp){
            Arrays.fill(d, -2);
        }
        
        // Simulation approach, balldrop from each position.
        for(int i=0; i<ans.length; i++){
            ans[i] = ballDrop(0, i);
        }
        
        return ans;
    }
}