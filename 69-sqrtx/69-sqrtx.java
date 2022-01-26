/*
explanation: simple root finder using natural log and exponent power functions! Uses the fact that taking log on both side of equations y^2 = x can give y as e^(log(x)/2), calculating this and performing calibration for boundary line numbers will give the square root in integer.

testcase:
4 -> Works
8 -> Works

Time & Space Complexity: O(1) and O(1), Since it only uses mathematical functions exponent and logarithm.
*/

class Solution {
    public int mySqrt(int x) {
        double r = Math.log(x) * 0.5;
        double o = Math.exp(r);
        
        // System.out.println(r);
        // System.out.println(o);
        
        double p = o - (int)o;
        if(p > 0.99999){
            o += 1; // Calibration for highly approaching numbers
        }
        
        // System.out.println(p);
        
        return (int)o;
    }
}