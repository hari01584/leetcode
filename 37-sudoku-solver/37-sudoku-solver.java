/*
explanation: Sudoku solver using backtracking! firstly the program creates a list containing all the blank spaces and then using backtracking it tries to fill each space with one element and seeing if the move is viable, it does this for every box and backtracks if invalid board configuration is found. finally it returns the solution state.

testcase:
[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]] -> Works

Time & Space Complexity : O(9^(n*n)) and O(n^2), the time complexity is 9 raised to power n square since there can be maximum of n square empty boxes in sudoku and each empty box takes 9 different values! Also space complexity is O(n^2) since backtracking is used which maintains a stack of size O(n^2)

*/

class Solution {
    int[][] b = new int[9][9];
    public int pack(int a, int b){
        int p = (0xf & a)<<4 | (0xf & b);        
        return p;
    }
    public void unpack(int u){
        int a = (u >> 4) & 0xf;
        int b = (u) & 0xf;
    }
    
    List<Integer> empty = new ArrayList<>();
    public int invalidBoardConf(int lx, int ly){
        int[] cX = new int[10];
        int[] cY = new int[10];

        for(int k=0; k<9; k++){
            int eX = b[k][ly];
            int eY = b[lx][k];
            
            if(eX != -1){
                if(cX[eX] != 0) return 1;
                else cX[eX] = 1;
            }
            if(eY != -1){
                if(cY[eY] != 0) return 1;
                else cY[eY] = 1;
            }
        }
        
        int istar = (int)Math.floor(lx/3)*3;
        int ibound = ((int)Math.floor(lx/3) + 1)*3;
        int ystar = (int)Math.floor(ly/3)*3;
        int ybound = ((int)Math.floor(ly/3) + 1)*3;
        int[] cE = new int[10];

        for(int ix=istar; ix<ibound; ix++){
            for(int iy=ystar; iy<ybound; iy++){
                int c = b[ix][iy];
                if(c != -1){
                    if(cE[c] != 0) return 1;
                    else cE[c] = 1;
                }
            }
        }
        
        return 0;
    }
    
    int[][] soln = new int[9][9];
    
    public void addValidBoard(int ix, int iy){
        for(int i=0; i<b.length; i++)
          for(int j=0; j<b[i].length; j++)
            soln[i][j] = b[i][j];

        System.out.println("HURRAY VALID BOARD CONF FOUND");
        printBoard(ix, iy);
    }
    
    public void printBoard(int lx, int ly){
        System.out.println("spot "+lx+" "+ly);
        for(int i=0; i<b.length; i++){
            for(int j=0; j<b[0].length; j++){
                System.out.print(b[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
    
    public void sudoku(int lx, int ly){
        if(invalidBoardConf(lx, ly) == 1) return;
        if(empty.size() == 0){
            // Maybe board correct here
            // System.out.println("HURRAY VALID BOARD CONF FOUND");
            // printBoard(lx, ly);
            addValidBoard(lx, ly);
            return;
        }
        
        // printBoard(lx, ly);
        
        for(int i=1; i<=9; i++){
            int u = empty.remove(0);
            int x = (u >> 4) & 0xf;
            int y = u & 0xf;
            b[x][y] = i;
            sudoku(x, y);
            b[x][y] = -1;
            empty.add(0, u);
        }
    }
    public void solveSudoku(char[][] board) {     
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == '.'){
                    // Empty, add to our imp list
                    empty.add(pack(i, j));
                    b[i][j] = -1;
                }
                else{
                    b[i][j] = board[i][j] - '0';
                }
            }
        }
        
        sudoku(0, 0);

        for(int i=0; i<soln.length; i++){
            for(int j=0; j<soln[0].length; j++){
                board[i][j] = (char)(soln[i][j] + '0');
            }
        }
        
    }
}