/*
explanation: finding element in sorted matrix using double binary search, the algorithm first runs binary search in first element of matrix to find the suitable ranged and then finally runs binary search in columns to find if any given key exists or not!

testcase:
[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
34
-> Works
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;

        int s = 0;
        int e = r-1;
        int m = 0;
        
        int startEndx = 0;
        if(target >= matrix[e][0]){
            startEndx = e;
            if(target == matrix[e][0]) return true;
        }
        else{
            while(s<=e){
                m = (s + e) / 2;
                if(matrix[m][0] == target) return true;
                else if(matrix[m][0] > target){
                    e = m-1;
                }
                else if(matrix[m][0] < target){
                    s = m+1;
                }
            }
            startEndx = s-1;
        }
        
        if(target < matrix[0][0]){
            startEndx = 0;
        }
        
        System.out.println("S "+startEndx);
        
        s = 0;
        e = c - 1;
        while(s<=e){
            m = (s + e) / 2;
            if(matrix[startEndx][m] == target) return true;
            else if(matrix[startEndx][m] > target){
                e = m-1;
            }
            else if(matrix[startEndx][m] < target){
                s = m+1;
            }
        }

        return false;
    }
}