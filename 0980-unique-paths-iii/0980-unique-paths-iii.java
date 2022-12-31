/*
explanation: unique paths iii by backtracking! The program works by using standard backtracking and a bunch of helper functions to guide it around! The robot go through each grid and when it reaches final, it checks if it has visited all 0 cells! If yes then adds it to answer and continues crawling.

testcase: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]] -> Works

Time & Space Complexity: O(mn4^(m+n)) & O(n^2): Here each call to backtracking calls 4 other calls. thf. time complexity is 4^m+n, also since in base case each call takes n^2 time (to check if all nodes are visited), thf. time complexity is mn, and the overall tc is mn*4^m+n
*/
class Solution {
    int ans = 0;
    int[][] grid;
    int targetX = 0;
    int targetY = 0;
    int[][] visited;
    // HashMap<Integer, Boolean> visit = new HashMap<>();
    
    public boolean outtabounds(int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length) return true;
        return false;
    }
    
    public boolean visitedAll(){
        for(int i=0;i<visited.length; i++){
            for(int j=0;j<visited[0].length; j++){
                if(visited[i][j]==0 && grid[i][j] == 0) return false;
            }
        }
        
        return true;
    }
    
    public boolean checkSuccess(int i, int j){
        if(i == targetX && j == targetY){
            // Reach target
            // System.out.println("Reach target:"+i+" "+j);

            // Check if traverse all path!
            if(visitedAll()){
                ans++;
            }
            return true;
        }
        
        return false;
    }
    
    public void backpath(int x, int y){
        if(outtabounds(x, y)) return;
        if(grid[x][y] == -1) return; // Cannot walk over obstacle!
        
        if(checkSuccess(x, y)) return;
        
        if(visited[x][y] == 1) return;
        visited[x][y] = 1; // Visit this!
        
        // System.out.println("On "+x+" "+y);
                
        // Go to next 4 directions!
        backpath(x+1, y);
        backpath(x-1, y);
        backpath(x, y+1);
        backpath(x, y-1);
        
        visited[x][y] = 0;
    }
    
    public Pair<Integer, Integer> findStart(){
        int i=0;
        int j=0;
        for(i=0;i<grid.length; i++){
            for(j=0;j<grid[0].length; j++){
                if(grid[i][j] == 1) return new Pair<>(i, j);
            }
        }
        return new Pair<>(i, j);
    }
    
    public Pair<Integer, Integer> findEnd(){
        int i=0;
        int j=0;
        for(i=0;i<grid.length; i++){
            for(j=0;j<grid[0].length; j++){
                if(grid[i][j] == 2) return new Pair<>(i, j);
            }
        }
        return new Pair<>(i, j);
    }
    
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.visited = new int[grid.length][grid[0].length];
        
        Pair<Integer, Integer> p = findStart();
        int i = p.getKey();
        int j = p.getValue();
        
        p = findEnd();
        targetX = p.getKey();
        targetY = p.getValue();
        
        backpath(i, j);
        
        return ans;
    }
}