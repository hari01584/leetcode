/*
explanation: simple reverse word using java api operations! the program simply trims the string, replace multiple white spaces with single one and splits it! finally after reversing all words we return it.

testcase:
the sky is blue -> Works

Time & Space Complexity: O(n) & O(n): Due to split operation time complexity is O(n), also space complexity is O(n) due to need for storage of these words!
*/
class Solution {
    public String reverseWords(String s) {
        String[] parts = s.trim().replaceAll(" +", " ").split(" ");
        String rev = "";
        for(int i=parts.length-1; i>=0; i--){
            if(parts[i].length() < 0) continue;
            rev = rev + parts[i] + " ";
        }
        
        return rev.substring(0, rev.length() - 1);
    }
}