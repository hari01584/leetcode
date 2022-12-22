class Solution {
    public int get(int[][] arr, int x, int y){
        if(x < 0 || y < 0) return 0;
        return arr[x][y];
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        for(int i=0; i<obstacleGrid.length; i++){
            for(int j=0; j<obstacleGrid[0].length; j++){
                obstacleGrid[i][j] = 1 - obstacleGrid[i][j]; // Invert
            }
        }
        
        boolean good = true;
        for(int z=0; z<obstacleGrid[0].length; z++){
            if(!good) obstacleGrid[0][z] = 0;
            if(obstacleGrid[0][z] == 0){
                good = false;
            }
        }
        
        good = true;
        for(int z=0; z<obstacleGrid.length; z++){
            if(!good) obstacleGrid[z][0] = 0;
            if(obstacleGrid[z][0] == 0){
                good = false;
            }
        }
        
        for(int i=0; i<obstacleGrid.length; i++){
            for(int j=0; j<obstacleGrid[0].length; j++){
                if(obstacleGrid[i][j] == 0) continue;
                if(i==0 || j==0){
                    continue;
                }
                
                obstacleGrid[i][j] = get(obstacleGrid, i-1, j) + get(obstacleGrid, i, j-1);
                
                System.out.println("Setting "+i+" "+j+" to "+obstacleGrid[i][j]);
            }
        }
        
        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}