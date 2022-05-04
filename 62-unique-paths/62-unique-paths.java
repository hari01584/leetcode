/*
explanation: finding max path using dynamic programming tabulation method, here the dynamic programming is used with the formula that dp[x][y] = dp[x-1][y] + dp[x][y-1] for x, y != 0 else 1 for x, y == 0, therefore in dp table the edges are filled with 1 and any other box is the sum of two adjacent blocks, finally we get the solution by seeing the value at bottom right box.

testcase:
1
1
Works
5
4
Works

Time & Space Complexity: O(mn) and O(mn)
Since a two linear nested loops of size m and n are run, the time complexity is O(mn) and since an array of size m x n is allocated therefore space complexity is also O(mn)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0 || j==0){
                    // Edge case, set to one
                    arr[i][j] = 1;
                }
                else{
                    arr[i][j] = arr[i-1][j] + arr[i][j-1];
                }
            }
        }
        
        return arr[m-1][n-1];
    }
}