/*
explanation: add two binary strings using three stacks! the program converts the string to stacks of integer, then it uses a recursive loop to add top most elements of both stacks and push it to new output stack! any carry is used in next recursive call and hence be utilized. this stack is finally converted to string  and returned.

testcase:
"1"
"0"
Stack top b1 and b2 are 1 and 0 respectively, now this is added and the result (1), is pushed to new output stack and returned as string.

"1"
"1"
In first addition we get 10, so we add 0 to output stack and in the next recursive loop it checks for carry, since carry is there and recursive call is about to end then it pushes an extra 1, therefore output stack become 1 0

"1010"
"1011"
Top at first recursive call 0, 1, after addition it becomes 1, this is added to out stack, next recursive loop 1 and 1 are added and 0 is pushed to out while 1 as carry is passed to next recursive loop, it goes on and the final stack becomes 10101

Time & Space Complexity:
Time Complexity is O(n) since the number of steps are for converting string 1 and 2 to Stacks and then a simple recursive call to add stack to output.

Space Complexity is O(n) as well, since three stacks are maintained to correctly calculate the binary strings.
*/
class Solution {
    void binaryStackAddition(Stack<Integer> b1, Stack<Integer> b2, int carry, Stack<Integer> out){
        if(b1.empty() && b2.empty()){
            if(carry==1) out.push(1);
            return;
        }
        int t, d;
        if(b1.empty()) t=0;
        else t = b1.pop();
        
        if(b2.empty()) d=0;
        else d = b2.pop();
        
        int sum = t + d + carry;
        if(sum == 3){
            sum = 1;
            carry = 1;
        }
        else if(sum == 2){
            sum = 0;
            carry = 1;
        }
        else{
            carry = 0;
        }
        
        out.push(sum);
        binaryStackAddition(b1, b2, carry, out);
    }
    
    public String addBinary(String a, String b) {
        Stack<Integer> b1 = new Stack<Integer>();
        Stack<Integer> b2 = new Stack<Integer>();
        
        for(int i=0; i<a.length(); i++){
            b1.push(a.charAt(i) - '0');
        }
        
        for(int i=0; i<b.length(); i++){
            b2.push(b.charAt(i) - '0');
        }
        
        Stack<Integer> out = new Stack<Integer>();
        binaryStackAddition(b1, b2, 0, out);
        
        String s = "";
        while(!out.empty()){
            int e = out.pop();
            s += String.valueOf(e);
        }
        
        return s;
    }
}