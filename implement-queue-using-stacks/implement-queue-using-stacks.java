/*
Explanation: Queue using stack DS, Costly pop and peek! Using two stacks to transfer elements to queue appropriately and hence implementing queue operations.

Testcase:
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Works
*/

class MyQueue {
    Stack<Integer> stack;
    public MyQueue() {
        stack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
    }
    
    public int pop() {
        Stack<Integer> temp = new Stack<Integer>();

        int x;
        while(true){
            x = stack.pop();
            if(stack.size() == 0) break;
            temp.push(x);
        }
        while(temp.size() > 0){
            int element = temp.pop();
            stack.push(element);
        }

        return x;
    }
    
    public int peek() {
        Stack<Integer> temp = (Stack<Integer>)stack.clone();
        int x;
        while(true){
            x = temp.pop();
            if(temp.size() == 0) break;
        }
        return x;
    }
    
    public boolean empty() {
        return stack.size() == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */