/*
explanation: https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
*/

class Solution {
    
    // https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
    public int[] getTable(String s){
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;
        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = table[i-1] + 1;
                index ++;
            }else{
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the 
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char 
                    index ++ ;
                }

                table[i] = index;
            }

        }

        return table;
    }

    
    public String shortestPalindrome(String s) {
        if(s.length() <= 1) return s;
        
        String stt = s+"$"+new StringBuilder(s).reverse().toString();
        // System.out.println(stt);
        int[] t = getTable(stt);
        // System.out.println(Arrays.toString(t));

        String expl = s.substring(t[t.length-1], s.length());

        StringBuilder sb=new StringBuilder(expl);  
        sb.reverse();  
        sb.append(s);
        
        return sb.toString();
    }
}