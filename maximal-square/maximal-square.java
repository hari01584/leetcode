/*
Finding largest submatrix in a matrix using Better Dynamic Programming approach (Depicted in solution), the program works by maintaining a 1D array containing 2 required neighbours of an activated (1) element and one cross neighbour saved as cache (or prev), The program works by first copying 1st row of input to the dp array (since the largest square matrix possible with bottom right element is always 1 or 0), likewise the contribution of first element in each row is also the same (since largest matrix made using 1st col elements is always 1 or 0). Next loop is run over each element of matrix and the dp array is updated. A single variable max stores the maximum value which is found in any iteration of loop, the data of arr dp is rewritten every row.

Testcases:
[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
steps:
1. init array dp with ["1","0","1","0","0"]
2. start looping from 2nd element of second array (order: 0->1->1->1) and update dp accordingly using the prev and dp values.
3. update dp and check for the maximum value, if dp greater than max then update max = dp
4. return max*max as square area
*/

class Solution {
    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }
    
    public int maximalSquare(char[][] matrix) {
        char[] scan = matrix[0];
        int cols = scan.length;
        int rows = matrix.length;
        
        int[] dp = new int[cols];
        int max = 0;
        for(int i=0; i<cols; i++){
            dp[i] = matrix[0][i]=='1'?1:0;
            max = Math.max(max, dp[i]);
        }
        
        for(int i=1; i<rows; i++){
            int prev = dp[0];
            dp[0] = matrix[i][0]=='1'?1:0;
            max = Math.max(max, dp[0]);
            for(int j=1; j<cols; j++){
                if(matrix[i][j] == '1'){
                    int t = dp[j];
                    dp[j] = (int)min(prev, dp[j], dp[j-1]) +1;
                    prev = t;
                }
                else{
                   dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max*max;
    }
}