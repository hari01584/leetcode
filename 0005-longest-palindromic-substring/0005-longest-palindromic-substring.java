class Solution {
    String str;
    String ans="";
    
    public boolean outbounds(int c1, int c2){
        return (c1 < 0 || c2 < 0 || c1 >= str.length() || c2 >= str.length());
    }
    
    public int dypalin(int c1, int c2){
        if(outbounds(c1, c2)){
            return 0;
        }
        
        int lp = c1;
        int rp = c2;
        while(true){
            if(outbounds(lp, rp)){
                // Outside bounds! break!
                break;
            }
            // Check if two characters same!
            if(str.charAt(lp) != str.charAt(rp)){
                // Not equal, break! and this might be the largest string of length!
                // Check for largest?
                break;
                // return rp - lp - 1;
            }
            
            lp--;
            rp++;
        }
        
        // Will reach here only when limits break, ie ends reached!
        // System.out.println("Palindrome found "+str.substring(lp+1, rp));
        int n = rp - lp - 1;
        if(n > ans.length()){
            ans = str.substring(lp+1, rp);
        }
        return n;
    }
    
    public String longestPalindrome(String s) {
        if(s.length()<=1) return s;
        
        this.str = s;
        
        for(int i=0; i<s.length(); i++){
            dypalin(i, i);
            dypalin(i, i+1);
        }
        
        return ans;
    }
}