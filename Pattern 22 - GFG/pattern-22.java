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
            obj.printSquare(n);
        }
    }
}
// } Driver Code Ends


class Solution {

    void printSquare(int n) {
        int k = 2*n - 1;
        for (int i=0; i<k; i++) {
            for (int j=0; j<k; j++) {
                System.out.print(Math.max(Math.abs(n-i-1), Math.abs(n-j-1))+1 + " ");
            }
            System.out.println();
        }
    }
}