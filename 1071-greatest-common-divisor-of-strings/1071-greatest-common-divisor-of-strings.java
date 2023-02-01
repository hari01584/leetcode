/*
explanation: gcd - https://leetcode.com/problems/greatest-common-divisor-of-strings/discuss/516621/Java-clean-solution-(0-ms)-100
*/
class Solution {
    int gcd(int a, int b)
    {
        // stores minimum(a, b)
        int i;
        if (a < b)
            i = a;
        else
            i = b;
 
        // take a loop iterating through smaller number to 1
        for (i = i; i > 1; i--) {
 
            // check if the current value of i divides both
            // numbers with remainder 0 if yes, then i is
            // the GCD of a and b
            if (a % i == 0 && b % i == 0)
                return i;
        }
 
        // if there are no common factors for a and b other
        // than 1, then GCD of a and b is 1
        return 1;
    }

    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";
        
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }
    
}