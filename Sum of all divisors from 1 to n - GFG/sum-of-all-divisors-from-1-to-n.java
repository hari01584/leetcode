//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0)
        {
            int N=sc.nextInt();
			
            
            Solution ob = new Solution();
            long ans  = ob.sumOfDivisors(N);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


/*
explanation: sum of divisors using contribution method, this calculates contribution of each of the number to final summations
and thus adds it all.

method -> https://www.geeksforgeeks.org/sum-divisors-1-n/
*/
class Solution{
    static long sumOfDivisors(int N){
        long sum = 0;
        for (int i=1; i<=N; i++) {
            sum += N - (N%i);
        }
        return sum;
    }
}