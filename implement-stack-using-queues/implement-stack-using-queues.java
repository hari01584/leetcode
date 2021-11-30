/*
Explaination: Stack using queue data structure, By maintaining one queue and using one more backup queue to save and reshuffle data, we can make stack! Push operations are simple here but in pop and peek another stack is needed to store and transfer elements.

Testcase:
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Works

*/

class MyStack {
    Queue<Integer> q;
    
    public MyStack() {
        q = new LinkedList<>();
    }
    
    public void push(int x) {
        q.add(x);
    }
    
    public int pop() {
        Queue<Integer> temp = new LinkedList<>(q);
        q.clear();
        int r;
        while(true){
            r = temp.remove();
            if(temp.size() == 0) break;
            q.add(r);
        }
        return r;
    }
    
    public int top() {
        Queue<Integer> temp = new LinkedList<>(q);
        int r;
        while(true){
            r = temp.remove();
            if(temp.size() == 0) break;
        }
        return r;
    }
    
    public boolean empty() {
        return q.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */