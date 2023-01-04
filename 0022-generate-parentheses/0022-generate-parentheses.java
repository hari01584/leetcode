/*
explanation: generate parenthesis by using the generator function and recursively applying string operations on them!

testcase: 3 -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic since each loop base function iterates 2 times deeper into program, space complexity is linear since stack space required!
*/
class Solution {    
    public HashSet<String> _generateParenthesis(int n) {
        if(n==1){
            return new HashSet<String>(Arrays.asList("()"));
        }
        HashSet<String> res = new HashSet<String>();
        HashSet<String> next = _generateParenthesis(n-1);
        for(String st : next){
            for(int i=0; i<st.length(); i++){
                String newst = st.substring(0, i) + "()" + st.substring(i, st.length());
                res.add(newst);
            }
        }
        return res;
    }
    
    public List<String> generateParenthesis(int n) {
        HashSet<String> ans = _generateParenthesis(n);
        return new ArrayList<>(ans);
    }
}