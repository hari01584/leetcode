/*
explanation: stockspanner using two stacks! the program works by maintaining a stack of inserted element and everything a new element is added it first compares elements from stack and pops until it can find span, after calculating it the program simply push all the leftover items to stack!

testcase:
["StockSpanner","next","next","next","next","next","next","next"]
[[],[100],[80],[60],[70],[60],[75],[85]] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic since in worst case we might have to remove all elements from original stack, also space complexity is linear since it stores all the elements in stack!
*/

class StockSpanner {
    class Item{
        int price;
        int span;
        public Item(int p, int s){
            price = p;
            span = s;
        }
    }
    Stack<Item> stack;
    Stack<Item> temp;
        
    public StockSpanner() {
        stack = new Stack<>();
        temp = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        
        while(!stack.empty() && price >= (stack.peek().price)){
            Item e = stack.pop();
            span += e.span;
        }
        
        
        stack.push(new Item(price, span));
        
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */