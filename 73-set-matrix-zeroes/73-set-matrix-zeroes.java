/*
explanation: records all the rows and columns with 0 in between and finally runs another loop to set every element in specific row and column to 0.

testcases:
[[1,1,1],[1,0,1],[1,1,1]]
Records that 0 is in 2st row and 2nd column of matrix, correspondingly it set's everything there as 0 as well.
*/

class Solution {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        
        ArrayList<Integer> rE = new ArrayList<Integer>();
        ArrayList<Integer> cE = new ArrayList<Integer>();
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(matrix[i][j] == 0){
                    rE.add(i);
                    cE.add(j);
                }
            }
        }
        
        for(int rm : rE){  
            for(int j=0; j<c; j++){
             matrix[rm][j] = 0;
            }
        }

        for(int cm : cE){  
            for(int i=0; i<r; i++){
             matrix[i][cm] = 0;
            }
        }
    }
}