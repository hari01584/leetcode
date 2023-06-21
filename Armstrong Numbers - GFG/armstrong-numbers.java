//{ Driver Code Starts
//Initial Template for Java
import java.util.*;
import java.io.*;
class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(read.readLine());
            Solution ob = new Solution();

            System.out.println(ob.armstrongNumber(n));
        }
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution {
    static String armstrongNumber(int n){
        int k = n;
        int sum = 0;
        while (k > 0) {
            int dig = k%10;
            sum += dig * dig * dig; // Cube and add
            k = k/10;
        }
        
        if (sum == n) {
            return "Yes";
        } else {
            return "No";
        }
    }
}