/*
explanation: works by using recursion to quick calculate square of numbers power until power remains less than one, then uses java funtion to calculate it and return.

testcase: -2 10 works
*/
class Solution {
    double xr = 0;
    double nr = 0;
    double recurMulti(double x, double n){
        if(n > 1){
            return recurMulti(x*x, n/2);
        }
        xr = x;
        nr = n;
        return 1;
    }
    
    public double myPow(double x, int n) {
        boolean isInv = false;
        if(n < 0){
            return Math.pow(x, n);
        }
        double d = recurMulti(x, n);
        
        boolean isNeg = false;
        if(x < 0 && n%2!=0){
            isNeg = true;
        }
        
        double v = Math.pow(xr, nr);
        if(isInv){
            v = 1 / v;
        }
        if(isNeg){
            v = -v;
        }
        // System.out.println(d);
        // System.out.println(xr);
        // System.out.println(nr);
        // System.out.println(isNeg);
        
        return v;
    }
}