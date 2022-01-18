/*
explanation: converts roman number to integer one using one stack and in O(n) time!, while scanning the roman string it scans it letter by letter and appends into a stack (of integers), the stack checks the value of topmost element, if this element is less than current to be added ie to be add is V and topmost is I, then it pops the V and substract the new value! After making this stack we add all element together and finally get answer.

testcase:
MCMXCIV => Works
Scanning from left to right in order M, C, M, X, C, I, V. for first character M, it is added to stack(note 1000 corresponding M is added, not letter M) (as it is empty), subsequent inserts check the value of previous inserts and add normally in stack (if top is not less than current element) or substracts the value!
*/

class RomanStack {
    Stack<Integer> _stack;
    char top = 'a';
    char sub = 'a';
    
    RomanStack(){
        _stack = new Stack<Integer>();
    }
    int fenc(char c){
        if(c=='I') return 1;
        else if(c=='V') return 5;
        else if(c=='X') return 10;
        else if(c=='L') return 50;
        else if(c=='C') return 100;
        else if(c=='D') return 500;
        else if(c=='M') return 1000;
        return -1;
    }
    void push(char c){
        if(_stack.empty()){
            top = c;
            _stack.push(fenc(c));
            return;
        }
        int i = fenc(c);
        int top = _stack.peek();
        if(top < i){
            // Lesser than
            top = _stack.pop();
            top = i - top;
            _stack.push(top);
        }
        else{
            _stack.push(i);
        }
    }
    
    int addAll(){
        int s = 0;
        while(!_stack.isEmpty()){
            s += _stack.pop();
        }
        return s;
    }
}

class Solution {
    public int romanToInt(String s) {
        RomanStack stack = new RomanStack();
        for(int i=0; i<s.length(); i++){
            stack.push(s.charAt(i));
        }
        return stack.addAll();
    }
}