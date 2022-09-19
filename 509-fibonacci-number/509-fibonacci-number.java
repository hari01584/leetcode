/*
explanation: Fibbonaci number calculator by recursive calculating fibbonaci of n-1 and n-2 terms, for the base case it uses 0 and 1 which returns it respectively!

testcase: n=2 -> 1 + 0 = 1

Time & Space Complexity: O(n) & O(n) respectively: Since using tree recursion with memoization we reduce the time complexity of this program to linear, also since recursion is used therefore space complexity is also linear!
*/
class Solution {
    int[] fibn;
    public int fibrec(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        if(fibn[n] != -1) return fibn[n];
        
        return (fibn[n] = fib(n-1) + fib(n-2));
    }
    
    public int fib(int n){
        fibn = new int[n+1];
        Arrays.fill(fibn, -1);
        return fibrec(n);
    }
}