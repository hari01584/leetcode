/*
explanation: ugly number using recursive divide approach! The program works by dividing the number by 2, 3 or 5 (whatever applies) and checking the remainder! At any point if by repeatedly dividing the number again and again there is something left (except 1), then we can say that this (which is also prime factor other than 2, 3, 5), violates the condition and thus return false!

testcase: 6 -> true

Time & Space Complexity: time complexity of this program is logarithmic, while space complexity is logarithmic due to the minimum halving of number and steps at each steps!
*/
class Solution {
    public boolean divide(int n){
        if(n == 1) return true;
        
        if(n%2==0) return divide(n/2);
        else if(n%3==0) return divide(n/3);
        else if(n%5==0) return divide(n/5);
        
        return false;
    }
    
    public boolean isUgly(int n) {
        if(n==0) return false;
        return divide(n);
    }
}