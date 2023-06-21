//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String S[] = read.readLine().split(" ");
            Long A = Long.parseLong(S[0]);
            Long B = Long.parseLong(S[1]);

            Solution ob = new Solution();
            Long[] ptr = ob.lcmAndGcd(A,B);
            
            System.out.print(ptr[0]);
            System.out.print(" ");
            System.out.println(ptr[1]);
        }
    }
}
// } Driver Code Ends

/*
explanation: gcd and lcm findinder using euclidean method! This uses gcd's euclidean method to find gcd which says that gcd of
two number, ie a and b will be equaal to gcd of greater-smaller and smaller number, this goes until one number becomes 0 and thus
the other one will be our gcd!
*/

class Solution {
    static long gcd(long a, long b) {
        if (b == 0) return a;
        
        long max = Math.max(a, b);
        long min = Math.min(a, b);
        
        return gcd(max-min, min);
    }
    
    static Long[] lcmAndGcd(Long A , Long B) {
        // Gcd by euclidean
        long gcd = gcd(A, B);
        long lcm = (A * B) / gcd;
        
        return new Long[]{lcm, gcd};
    }
};