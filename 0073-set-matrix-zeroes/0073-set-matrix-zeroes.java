/*

*/
class Solution {
    public void setZeroes(int[][] matrix) {
        // for first iteration, mark matrix rows or column for zero setting by settings its first element
        // in both row and column to zero.. ie!
        
        boolean set_first_colm_zero = false;
        boolean set_first_row_zero = false;
        
        for (int i=0; i<matrix.length; i++)
            if (matrix[i][0] == 0)
                set_first_colm_zero = true;
        
        for (int j=0; j<matrix[0].length; j++)
            if (matrix[0][j] == 0)
                set_first_row_zero = true;
        
        // First we mark the flags for inner matrix (ie without rows and columns marking)
        for (int i=1; i<matrix.length; i++) {
            for (int j=1; j<matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    // Mark both row and column for deletion later on, ie
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        
        // Now iterate over matrix once again and delete any element which has column or row marked! ie
        for (int i=matrix.length-1; i>=1; i--) {
            for (int j=matrix[i].length-1; j>=1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    // Delete this
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Similiarly set first column and row to zero
        if (set_first_colm_zero) {
            for (int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }

        if (set_first_row_zero) {
            for (int j=0; j<matrix[0].length; j++)
                matrix[0][j] = 0;
        }
    }
}