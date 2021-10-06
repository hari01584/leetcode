/*
Circular queue using modulo operator (%), Data is mapped internally to array and a circular indexing is followed using the modulo operator, When queue is empty front and rear is set to -1 (As a flag), The implementation similiarly detected emptyfull queue using the front and rear indexes.

NOTE: r,f detones the current position of FILLED node they are pointing, not the next node to be FILLED/EMPTIED.

Testcases:
["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Front","isFull","deQueue","enQueue","Rear"]
[[3],[1],[2],[3],[4],[],[],[],[4],[]]

Works, MyCircularQueue (3) -> Make Queue with size 3, ie index 0, 1, 2
enQueue(1) -> adds 1 to the array so arr = [1, 0, 0] and r,f becomes -> {0, 0} respectively.
enQueue(2) -> adds 2 to the array so arr = [1, 2, 0] and r,f becomes -> {1, 0} respectively.
enQueue(3) -> adds 3 to the array so arr = [1, 2, 3] and r,f becomes -> {2, 0} respectively.

enQueue(4) -> since (2+1)%3 becomes equal to f(0 here), ie the condition of isFull is triggered, therefore no insertion is done and the function call returns false. r,f -> {2, 0}

Front() -> Simply peeks front element, or arr(f) or arr(0), which is equal to 1, thf. output is 1.

isFull() -> Again, as already seen in step enQueue(4) previously, the expression (r+1)%bucket_size == f becomes true, thf. boolean true is returned.

deQueue() -> Checks for empty, f==-1, not empty therefore sets the position of f to next circular index (ie (f+1) % bucket_size), If however after doing this, the f becomes equal to r(ie the queue is essentially empty now) then both f and r is set to -1. Here r,f -> {2, 1}

enQueue(4) -> Since the isFull condition becomes false now (as f is 1 now), We add 4 to the next possible circular index (or index 0), therefore the arr = [4, 2, 3] and r,f becomes -> {0, 1} respectively.

*/

class MyCircularQueue {
    int arr[];
    int f, r;
    int bucket_size;
    public MyCircularQueue(int k) {
        arr = new int[k];
        f = -1;
        r = -1;
        bucket_size = k;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        if(r==-1){ f=0; }
        
        r = (r+1)%bucket_size;
        arr[r] = value;
        
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;
        
        if(f==r){ r=-1; f=-1; }
        else
        f = (f+1) % bucket_size;
        
        return true;
    }
    
    public int Front() {
        return f==-1?f:arr[f];
    }
    
    public int Rear() {
        return r==-1?r:arr[r];
    }
    
    public boolean isEmpty() {
        return f==-1;
    }
    
    public boolean isFull() {
        return (r+1)%bucket_size == f;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */