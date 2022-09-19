/*
explanation: nth trifibonacci by recursive dynamic programming, it uses memoization with basic recursive clause to do it.

testcase: 37 -> Works

Time & Space Complexity: O(n) & O(n): Time complexity is linear since memoization reduces it from 3^n to linear, also space complexity is also linear due to maintained memoizaed variable.
*/

class Solution {
    int[] fibn;
    public int tribonaccirec(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 1;
        
        if(fibn[n] != -1) return fibn[n];
            
        return (fibn[n] = tribonaccirec(n-1) + tribonaccirec(n-2) + tribonaccirec(n-3));
    }
    
    public int tribonacci(int n){
        fibn = new int[n+1];
        Arrays.fill(fibn, -1);
        
        return tribonaccirec(n);
    }
}