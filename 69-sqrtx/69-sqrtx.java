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