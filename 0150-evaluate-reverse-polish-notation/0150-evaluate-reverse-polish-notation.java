/*
explanation: evaluate reverse polish notation using stack method! The program works by implementing one of the two cases for each element in token list:
- If it is number, then simply push it.
- If it is an operator then pop two elments, perform operation and then push it!
Doing this and finding the topmost element of stack will give the answer of all operations!

testcase: ["2","1","+","3","*"] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear because a linear loop is used, also space complexity is linear due to max stack space of n elements present.
*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i=0; i<tokens.length; i++){
            String tk = tokens[i];
            // System.out.println(tk);
            try{
                Integer val = Integer.parseInt(tk);
                stack.push(val);
            } catch (Exception e){
                int o1 = stack.pop();
                int o2 = stack.pop();
                int res=0;
                
                if(tk.equals("+")){
                    res = o1+o2;    
                } else if(tk.equals("-")){
                    res = o2-o1;
                } else if(tk.equals("*")){
                    res = o1*o2;
                } else if(tk.equals("/")){
                    res = o2/o1;
                }
                
                stack.push(res);
                
                // System.out.println("o1 "+o1+" o2 "+o2+" res "+res);
            }
            
            System.out.println(tk);
        }
        
        return stack.peek();
    }
}