/*
explanation: This code works by partitioning any matrix into possible regions and then considering
each and everyone to be possible answers, It is brute force approach!
*/
class Solution {
    int answer; // Our global answer object

    public int find_min_rect_sum(int xs, int ys, int xe, int ye, int[][] grid) {
        int xm = -1;
        int ym = -1;
        int xsm = 100000;
        int ysm = 100000;
        for (int i=xs; i<=xe; i++) {
            for (int j=ys; j<=ye; j++) {
                int e = grid[i][j];
                if (e == 1) {
                    xm = Math.max(xm, i);
                    ym = Math.max(ym, j);
                    xsm = Math.min(xsm, i);
                    ysm = Math.min(ysm, j);
                }
            }
        }
        
        // If not found any, ie xm == -1, simply return 0
        if (xm == -1) {
            return 0;
        }
        
        return (xm - xsm + 1) * (ym - ysm + 1);
    }
    
    public void partition_grid(int xs, int ys, int xe, int ye, int sum, int pleft, int[][] grid) {
        if (pleft <= 0) {
            // Add remaining one area
            sum += find_min_rect_sum(xs, ys, xe, ye, grid);
            // System.out.println("Terminating with sum: "+sum);
            answer = Math.min(answer, sum);

            return;
        }

        // System.out.println("Now xs:"+xs+" ys:"+ys+" xe:"+xe+" ye:"+ye+" sum: "+sum+" pleft:"+pleft);
        // Let's partition and find our answer! First try to partition on all possible colmns
        for (int yp = ys+1; yp <= ye; yp++) {
            int left_part_sum = find_min_rect_sum(xs, ys, xe, yp-1, grid);
            int right_part_sum = find_min_rect_sum(xs, yp, xe, ye, grid);
            // If we split up on yp, then simply restrict starting point from ys to yp and run again!
            partition_grid(xs, yp, xe, ye, sum + left_part_sum, pleft-1, grid); // Take left and p on r
            partition_grid(xs, ys, xe, yp-1, sum + right_part_sum, pleft-1, grid); // Take right, p on l
        }
        
        // Also partition on rows as well...
        for (int xp=xs+1; xp <= xe; xp++) {
            int upper_part_sum = find_min_rect_sum(xs, ys, xp-1, ye, grid);
            int lower_part_sum = find_min_rect_sum(xp, ys, xe, ye, grid);
            partition_grid(xp, ys, xe, ye, sum + upper_part_sum, pleft-1, grid);
            partition_grid(xs, ys, xp-1, ye, sum + lower_part_sum, pleft-1, grid);
        }
    }
    
    public int minimumSum(int[][] grid) {
        answer = 100000000;
        partition_grid(0, 0, grid.length - 1, grid[0].length - 1, 0, 2, grid);

        return answer;
    }
}