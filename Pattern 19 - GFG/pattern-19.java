//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Main {
    // Driver code
    public static void main(String[] args) throws Exception {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            Solution obj = new Solution();
            obj.printTriangle(n);
        }
    }
}
// } Driver Code Ends


class Solution {

    void printTriangle(int n) {
        int k = n*2;
        for (int i=0; i<=k; i++) {
            if (i == n) continue;
            
            int sdraw = Math.abs(n - i);
            for (int z=0; z<sdraw; z++) {
                System.out.print("*");
            }
            
            int space = k - 2*sdraw;
            for (int z=0; z<space; z++) {
                System.out.print(" ");
            }
            
            for (int z=0; z<sdraw; z++) {
                System.out.print("*");
            }
            
            System.out.println();
        }
    }
}