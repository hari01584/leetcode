// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            String input_line[] = in.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            
            Solution ob = new Solution();
            System.out.println(ob.matrixMultiplication(N, arr));
        }
    }
}
// } Driver Code Ends


/*
explanation: https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

tabulation written by using patterns!
*/

class Solution{
    int[] arr;
    int[][] dp;
    int mcm(int i, int j){
        if(i == j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<j; k++){
            int mc1 = mcm(i, k);
            int mc2 = mcm(k+1, j);
            int mc = mc1 + mc2 + (arr[i-1] * arr[k] * arr[j]);
            min = Math.min(min, mc);
        }
        
        return dp[i][j]=min;
    }
    
    int matrixMultitabular(int N, int arr[]){
        int[][] dp = new int[N-1][N-1];
        

        for(int diag=0; diag<N-1; diag++){
            for(int i=0; i<N-1-diag; i++){
                int j = i+diag;
                // System.out.println(i+" "+j+" E");
                
                if(i==j) dp[i][j] = 0;
                else{
                    int min = Integer.MAX_VALUE;
                    for(int k=i; k<j; k++){
                        // System.out.println(i+" "+k+", "+(k+1)+" "+j);
                        // System.out.println(arr[i]+" "+arr[k]+", "+arr[j]);

                        int mc1 = dp[i][k];
                        int mc2 = dp[k+1][j];
                        int mc = mc1 + mc2 + (arr[i] * arr[k+1] * arr[j+1]);
                        min = Math.min(min, mc);
                    }
                    dp[i][j] = min;
                }
            }
            // System.out.println();
        }
        
        // System.out.println(Arrays.deepToString(dp));
        
        return dp[0][N-2];
    }
    
    int matrixMultiplication(int N, int arr[])
    {
        // this.arr = arr;
        // dp = new int[arr.length][arr.length];
        // for(int[] d:dp){
        //     Arrays.fill(d, -1);
        // }
        
        // return mcm(1, arr.length-1);
        return matrixMultitabular(N, arr);
    }
}