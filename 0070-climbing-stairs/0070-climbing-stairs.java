class Solution {
    public int climbStairs(int n) {
        int s1 = 1;
        int s2 = 2;
        if(n<=2) return n;
        
        n-=2;
        while(n>0){
            int ns = s1 + s2;
            s1 = s2;
            s2 = ns;
            n--;
        }
        
        return s2;
    }
}