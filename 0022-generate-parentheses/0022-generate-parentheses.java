/*
explanation: generate parentheses using dynamic programming! The algorithm works by crawling the string and using conditions to add both parenthesis! which is to always add left parenthesis if its leftcount (lp) is > 0, and only add right parenthesis if rightcount is greater than left parenthesis!

testcase: 3 -> Works

Time & Space Complexity: O(2^n) & O(n): Since each recursive calls 2 other function therefore max tc is 2 raised to power n, also space complexity is linear due to stack space required in algorithm!
*/
class Solution {
    List<String> ans = new ArrayList<>();
    
    void backparen(String str, int lp, int rp){
        if(rp < lp) return;
        if(lp == rp && rp == 0){
            // Return and add this element
            ans.add(str);
            return;
        }
        if(lp < 0 || rp < 0) return;
        
        // Add left paren to str
        backparen(str+"(", lp-1, rp);
        // Add right paren to str
        backparen(str+")", lp, rp-1);
    }
    
    public List<String> generateParenthesis(int n) {
        backparen("", n, n);
        return ans;
    }
}