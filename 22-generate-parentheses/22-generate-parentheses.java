/*
explanation: brackets generator using recursive algorithms! It generates all valid brackets use the following simple recursive loop: base condition of this function is set for n=1 which is (), and any subsequent n (for ex 2) will use the brackets in previous loop (ex n=1 for n=2) and insert a complete bracket () after each of characters and make it a new string, see testcases for better understanding.

testcase:
n=1 -> returns () as base case

n=2 -> uses n=1 brackets, ie () and inserts new pair of simple brackets () to after each of the character in previous loops, ie () + () = ()() + (()) + ()(), see how the complete bracket is inserted after each character in initial bracket expression! Unique values of such resulted algorithm is stored in a list and returned.

n=3 -> Uses n=2 brackets, ie ()(), (()) and inserts () to each of them in lists, to get all valid brackets.

Time & Space Complexity: Time complexity of this algorithm is n^2, since recursive loop requires all previous bracket expression and each of bracket expression is iterated (character by character), Space Complexity is O(n), since bracket expression list is needed to be stored in stack after each recursive depth call.
*/


class Solution {
    List<String> bracksGen(int n){
        if(n <= 1){
            return new ArrayList<String>(Arrays.asList("()"));
        }
        
        List<String> b = bracksGen(n-1);
        HashSet<String> hs = new HashSet<String>();
        for(String s:b){
            StringBuffer buffer = new StringBuffer(s);
            for(int i=0; i<s.length(); i++){
                buffer.insert(i + 1, "()");
                // System.out.println(buffer.toString());
                hs.add(buffer.toString());
                buffer.delete(i+1, i+3);
            }
        }
        
        return new ArrayList<>(hs);
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> r = bracksGen(n);
        
        // for(String s : r){
        //     System.out.println(s);
        // }
        
        return r;
    }
}