//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int choice = Integer.parseInt(in.readLine());
            String a[] = in.readLine().trim().split("\\s+");
            List<Double> arr = new ArrayList<Double>();
            for(int i = 0;i < choice;i++)
                arr.add(Double.parseDouble(a[i]));
            
            Solution ob = new Solution();
            if(choice == 1)
                System.out.println(String.format("%.2f", ob.switchCase(choice, arr)));
            else
                System.out.println((int)ob.switchCase(choice, arr));
        }
    }
}
// } Driver Code Ends


/*
explanation: area finder using switch case, the program works by switching over to choice and finding area accordingly.

testcase: 1 5 -> Works

Time & Space Complexity: O(1) & O(1): Both time and space are linear due to countable conditional calls and required with
no extra space.
*/
class Solution{
    static double switchCase(int choice, List<Double> arr){
        switch (choice) {
            case 1:
                double r = arr.get(0);
                return Math.PI * r * r;
            case 2:
                double l = arr.get(0);
                double b = arr.get(1);
                return l*b;
                
        }
                
        return 0.0;
    }
}