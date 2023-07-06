/*
explanation: valid palindrome using recursion and windows, the algorithm works by maintaining start and end and shrinking them as needed for palindrome, see comments below

testcase: "A man, a plan, a canal: Panama" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity are both linear because of iteration required to loop over each character of string and the  stack space needed for recursion!
*/
class Solution {
    static boolean isAlphaNumeric(char ch) {
        if ((ch >= '0' & ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            return true;
        return false;
    }
    
    public boolean recurpalin(String str, int start, int end) {
        // Uses recursive window technique to progressively go smaller and smaller
        // and check if string is palindrome! (See cases)
        if (start >= end) return true;
        
        // If left character is not to be taken, run same algo on start+1, end
        if (!isAlphaNumeric(str.charAt(start))) {
            return recurpalin(str, start + 1, end);
        }
            
        if (!isAlphaNumeric(str.charAt(end))) {
            return recurpalin(str, start, end - 1);
        }
        
        // Convert and check if smallcase letter
        if (Character.toLowerCase(str.charAt(start)) != Character.toLowerCase(str.charAt(end)))
            return false; // Not a palindrome
        
        // Shrink window by one in both sides and recursively check again!
        return recurpalin(str, start + 1, end - 1);
    }
    
    public boolean isPalindrome(String s) {
        return recurpalin(s, 0, s.length() - 1);
    }
}