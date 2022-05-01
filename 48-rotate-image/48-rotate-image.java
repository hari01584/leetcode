/*
explanation: in-place rotate clockwise by first transposing the matrix and then finally swapping each row elements! 

testcase:
[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]] -> Works
*/

class Solution {
    public void swap(int x, int y, int x1, int y1, int[][] m){
        int t = m[x][y];
        m[x][y] = m[x1][y1];
        m[x1][y1] = t;
    }
    
    public void rotate(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        
        // First transpose
        for(int i=0; i<r; i++){
            for(int j=0; j<i; j++){
                swap(i, j, j, i, matrix);
            }
        }
        
        // Then reverse
        for(int i=0; i<r; i++){
            int j=0;
            int l=c-1;
            while(l > j){
                swap(i, j, i, l, matrix);
                j++;
                l--;;
            }
        }
    }
}