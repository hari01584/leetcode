/*
explanation: word search problem by backtracking! The program works by maintaining checked list and using backtracking to crawl neighbours! At each element it looks for four directions and using the t pointer (to maintain index at word), if this is greater than word length then we say that the word is found in matrix!

testcase:
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"ABCCED" -> Works

Time & Space Complexity: O(4^n) & O(4^n): Since problem calls itself 4 times therefore worst time and space complexity is 4 raise to power n!
*/
class Solution {
    char[][] board;
    String word;
    int[][] marked;
    
    public boolean outBounds(int i, int j){
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0) return true;
        else return false;
    }
    public boolean wordSearch(int i, int j, int t){
        // System.out.println(i+" "+j+" "+t);
        if(t >= word.length()){
            // Either it match or return false!
            return true;
        }
        
        if(outBounds(i, j) || marked[i][j] == 1 || board[i][j] != word.charAt(t)){
            return false;
        }
        
        // Mark
        marked[i][j] = 1;
        boolean b = wordSearch(i+1, j, t+1) || wordSearch(i-1, j, t+1) || wordSearch(i, j+1, t+1) || wordSearch(i, j-1, t+1);
        // Unmark
        marked[i][j] = -1;
        return b;
    }
    public boolean exist(char[][] b, String w) {
        this.board = b;
        this.word = w;
        this.marked = new int[b.length][b[0].length];
        for(int i=0; i<b.length; i++){
            for(int j=0; j<b[0].length; j++){
                if(wordSearch(i, j, 0)) return true;
            } 
        }
        
        return false;
    }
}