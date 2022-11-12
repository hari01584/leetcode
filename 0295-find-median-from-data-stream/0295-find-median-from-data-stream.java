/*
explanation: median finder using two queues method! The program works by maintaining two queues and using the head of these two queues to get the median value, whenever a new number is to be inserted in program we perform set of operations relating to que1 and que2 to build the queue for median calculations!

testcase:
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]] -> Works

Time & Space Complexity: O(n) & O(n): time complexity of this program is linear since we iterate through each element once, also the space complexity is also linear because we have to maintain two queues having n elements!
*/
class MedianFinder {
    PriorityQueue<Integer> que1;
    PriorityQueue<Integer> que2;
    
    public MedianFinder() {
        que1 = new PriorityQueue<Integer>();
        que2 = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    public void debug(PriorityQueue<Integer> q){
        System.out.println(Arrays.toString(q.toArray()));
    }
    
    public void addNum(int num) {
        // System.out.println("Adding "+num);
        que1.offer(num);
        que2.offer(que1.poll());
        
        if(que1.size() < que2.size()){
            // Shift to que2!
            que1.offer(que2.poll());
        }
        
        // debug(que1);
        // debug(que2);
    }
    
    public double findMedian() {
        if((que1.size() + que2.size())%2==0){
            return (que1.peek() + que2.peek())/2.0;
        }
        return que1.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */