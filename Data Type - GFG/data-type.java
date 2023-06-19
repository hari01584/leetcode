//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.math.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            sc.nextLine();
            String s = sc.next();
            Solution obj = new Solution();
            int ans = obj.dataTypeSize(s);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


// User function Template for Java
/*
explanation: checking data type using switch statements, easy and returns the value!

testcase: Character -> Works

Time & Space Complexity: O(1) & O(1): Since no loops are required and there is constant
conditional checks, also no extra space is required therefore space complexity is also
linear.
*/
class Solution {
    static int dataTypeSize(String str) {
        switch (str) {
            case "Character":
                return 1;
            case "Integer":
                return 4;
            case "Long":
                return 8;
            case "Float":
                return 4;
            case "Double":
                return 8;
            default:
                return 0;
        }
    }
}