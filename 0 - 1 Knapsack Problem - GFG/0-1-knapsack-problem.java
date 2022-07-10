// { Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        //reading total testcases
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());
            
            int val[] = new int[n];
            int wt[] = new int[n];
            
            String st[] = read.readLine().trim().split("\\s+");
            
            //inserting the values
            for(int i = 0; i < n; i++)
              val[i] = Integer.parseInt(st[i]);
             
            String s[] = read.readLine().trim().split("\\s+"); 
            
            //inserting the weigths
            for(int i = 0; i < n; i++)
              wt[i] = Integer.parseInt(s[i]);
              
            //calling method knapSack() of class Knapsack
            System.out.println(new Solution().knapSack(w, wt, val, n));
        }
    }
}



// } Driver Code Ends


class Solution 
{ 
    static int[] wt;
    static int[] val;
    static int limit = 10;
    
    static HashMap<String, Integer> map = new HashMap<>();
    static int[][] dp;
    
    static String createbitstr(String str, int index, boolean isInclude){
        return str.substring(0, index) + (isInclude?"1":"0")
              + str.substring(index + 1);
    }
    static int knap(int W, int n, String identity){
        if(W <= 0 || n >= wt.length){
            return 0;
        }
        
        // System.out.println("getmas "+identity);
        // if(map.get(identity) != null){
        //     System.out.println("Saved you some slack! " + wt[n]);
        //     return map.get(identity);
        // }
        if(dp[n][W] != -1){
            // System.out.println("Saved you some slack! " + wt[n]);
            return dp[n][W];
        }
        
        // System.out.println("n "+n+" "+"Judgement "+wt[n]+" weight "+W);
        
        if(wt[n] > W){
            int v = knap(W, n+1, identity);
            // map.put(identity, v);
            return v;
        }
        else{
            int include = val[n] + knap(W-wt[n], n+1, createbitstr(identity, n, true));
            int exclude = knap(W, n+1, identity);

            // System.out.println("include "+include+", exclude "+exclude);

            int v = -1;
            if(include > exclude){
                v = include;
                // String mas = createbitstr(identity, n, true);
                // map.put(mas, v);
                // System.out.println("savemask "+mas);
            }
            else{
                v = exclude;
                // map.put(identity, v);
            }
            // Cache (ie memoize)
            
            dp[n][W] = v;
            
            return v;
        }
    }

    static int knapSack(int W, int w[], int v[], int n) 
    { 
         wt = w;
         val = v;
         dp = new int[n+1][W+1];
         for (int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
         }

         String s = String.format("%0" + n + "d", 0);
        //  System.out.println(s);
         return knap(W, 0, s);
    } 
}
