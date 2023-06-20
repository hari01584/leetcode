//{ Driver Code Starts
//Initial Template for Java


import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            int N = Integer.parseInt(read.readLine());
            Solution ob = new Solution();
            System.out.println(ob.evenlyDivides(N));
        }
    }
}
// } Driver Code Ends


/*
explanation: evenly divides, this algo works by taking up each digit one by one and also dividing number with digit like such!

testcase: 12 -> Works

Time & Space Complexity: O(log10(n)) & O(1): Time complexity is log due to division by 10 at each step, also space complexity is
constant due to finite extra variables used.
*/
class Solution{
    static int evenlyDivides(int N){
        int count = 0;
        int k = N;
        while(k > 0) {
            int digit = k%10;
            if(digit != 0 && N%digit==0) {
                count++;
            }
            k = k/10;
        }
        
        return count;
    }
}