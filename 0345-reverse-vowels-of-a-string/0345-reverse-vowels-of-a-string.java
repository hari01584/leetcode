/*
explanation: reverse vowels using 2p approach, finds the left and right most vowels in string and replace them, does it until all the cases are satisfied!

testcase: "hello" -> Works
first we start l pointer from left till we find vowel, ie "e"! Also r pointer willbe at "o", now we simply swap them and increase l and decrease r (ie shift them)! Does it until we get the answer.

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear since a linear iteration over array is required and that the space is required to store the swapped string!
*/

class Solution {
    boolean isVowel(char x)
    {
        if (x == 'a' || x == 'e' || x == 'i' ||
                          x == 'o' || x == 'u' ||
           x == 'A' || x == 'E' || x == 'I' ||
                          x == 'O' || x == 'U')
            return true;
        else
            return false;
    }
    
    public String reverseVowels(String s) {
        int l = 0;
        int r = s.length() - 1;
        
        StringBuilder sb = new StringBuilder(s);
        
        while(l<r){
            // Find left vowel
            for(;l<s.length();l++){
                if(isVowel(s.charAt(l))){
                    break;
                }    
            }
            
            // At this point left is found
            for(;r>0;r--){
                if(isVowel(s.charAt(r))){
                    break;
                }    
            }
            
            if(l >= r) break;
            
            // System.out.println(s.charAt(l)+" swap "+s.charAt(r));
            sb.setCharAt(l, s.charAt(r));
            sb.setCharAt(r, s.charAt(l));
            
            l++;
            r--;
        }
        
        return sb.toString();
    }
}