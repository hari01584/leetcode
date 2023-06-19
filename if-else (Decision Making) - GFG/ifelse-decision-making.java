//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while(t-- > 0)
        {
            int n = scn.nextInt();
            int m = scn.nextInt();
            Solution ob = new Solution();
            String ans = ob.compareNM(n,m);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


/*
explanation: simple decision making using comparison, uses n and m variables and check.

testcase: 4, 8 -> works

time & space complexity: O(1) & O(1): Since countable loops and no extra space is required.
*/
class Solution{
    static String compareNM(int n,int m){
        if (n < m) {
            return "lesser";
        } else if (n > m) {
            return "greater";
        } else {
            return "equal";
        }
    }
}