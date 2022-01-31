/*
explanation: Climbing stairs! Initially the program calculates all the 2 steps required to reach the top and then for every 2 steps required it iteratively converts them into a pair of single steps (since it's either 1+1 or 2 steps), now the number of ways of arranging ones and twos steps are calculated using Permutations and Combinations (in ways function), which is basically (ones + twos)!/(ones! * twos!) where ! is sign for factorial of number, applying this we get all the possible arrangements.

testcase:
7 -> Initially max 2 steps can be 3, and remaining one step is 1.. ie 3 (2 steps) + 1 (1 steps), now arrange these 3 + 1 kind of steps using Permutations we get total 4 ways! ie 1 2 2 2, 2 1 2 2, 2 2 1 2, 2 2 2 1, where 2 represent 2 steps and 1 represent 1 step, now once we figure out total ways of this combination, we dissolve one two step into 2 one steps, ie the configuration will change from 3 + 1 steps to 2 + 3 steps, ie 2 2 steps and 3 one steps.. 

Complexity: O(N) and O(1), since loop for factorial is used, thf. time complexity is o(n), and since countable variables are used the space complexity is O(1).
*/



import java.math.BigInteger;

class Solution {
    static int factorial(int n){    
      if (n <= 0)    
        return 1;    
      else    
        return(n * factorial(n-1));
    }
    public static double log2(int n)
    {
        return (Math.log(n) / Math.log(2));
    }

    public int ways(int ones, int twos){
        int t = ones + twos;
        
        int large = ones>twos?ones:twos;
        int small = ones<twos?ones:twos;
        
        if(large==0) large = 1;
        if(small==0) small = 1;
        
        BigInteger m = BigInteger.ONE;
        for(int i=large+1; i<=t; i++){
            m = m.multiply(BigInteger.valueOf(i));
        }
        
        BigInteger n = BigInteger.ONE;
        for(int i=1; i<=small; i++){
            n = n.multiply(BigInteger.valueOf(i));
        }
        
        BigInteger comb = m.divide(n);
        
        // System.out.println(ones + " " + twos + " " + m.intValue() + " " + n.intValue() + " "+comb);
        
        return comb.intValue();
    }
    
    public int climbStairs(int n) {
        int l2 = n/2;
        int l1 = n%2==0?0:1;
        
        int t = 0;
        for(int i=l2; i>=0; i--){
            t += ways(l1, l2);
            
            l2 -= 1;
            l1 += 2;
        }
                
        return t;
    }
}