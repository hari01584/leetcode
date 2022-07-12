// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    int Arr[] = new int[n];
                    for(int i = 0;i<n;i++)
                        Arr[i] = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.maxSumIS(Arr,n));
                }
        }
}    // } Driver Code Ends


/*
explanation: maximum increasing subsequence using dynamic programming!
it works by having two choice at any element, if current element is greater than previous element then
we have two choices, ie we can include or exclude it! Similiarly if not then we cannot include this e-
lement! therefore we can simply apply memoization and solve this problem!

testcase:
5
1 101 2 3 100
-> Works

Time & Space Complexity; O(n^2) and O(n^2): Since a memoized table is required which takes n^2 operation 
and space therefore the time complexity of application is quadratic!

*/
class Solution
{
    int[] arr;
    int dp[][];
    
    public int getElement(int[] arr, int index){
        if(index<0) return -1;
        return arr[index];
    }
    
    public int msum(int n, int prev){
        // Base case
        if(n >= arr.length) return 0;
        
        // Cache
        if(dp[n][prev] != -1) return dp[n][prev];
        
        if(arr[n] > arr[prev]){
            int include = arr[n] + msum(n+1, n);
            int exclude = msum(n+1, prev);
            return dp[n][prev] = Math.max(include, exclude);
        }
        else{
            return dp[n][prev] = msum(n+1, prev);
        }
    }
	public int maxSumIS(int arr[], int n)  
	{  
	    int[] newArray = Arrays.copyOf(arr, arr.length + 1);
        newArray[0] = 0;
        System.arraycopy(arr, 0, newArray, 1, arr.length);

	    this.arr = newArray;
	    this.dp = new int[n+1][n+1];
	    for(int[] d : dp){
	        Arrays.fill(d, -1);
	    }
	    
	    return msum(1, 0);
	}  
}