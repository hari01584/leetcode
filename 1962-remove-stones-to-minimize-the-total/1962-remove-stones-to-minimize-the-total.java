/*
explanation: removes stone to minimize total using greedy approach! The program works by maintaining a max heap where for each operation we do the following: we chose the max element in heap, half it and add it back! This works on the principle that the maximum stones removed can only be acheived when removing most stones, (ie if you remove half stone, you do it on a pile with maximum stone, so max/2 will also be maximum)!

testcase: [5,4,9] k=2 -> Works

Time & Space Complexity: o(nlogn) & O(n): time complexity of this program is nlogn as we iterate through each element in array once and the insertion operation in maxheap takes logn time, thf. time complexity is nlogn, also space complexity is linear due to the storage required to store queue!
*/
class Solution {
    public int minStoneSum(int[] piles, int k) {
        int sum=0;
        PriorityQueue<Integer> maxheap = new PriorityQueue(Collections.reverseOrder());
        for(int p : piles){
            sum+=p;
            maxheap.add(p);
        }
        while(k > 0){
            // Remove max element, half it and add to queue!
            int max = maxheap.poll();
            if(max == 1) return piles.length;
            
            int removed = (int)Math.floor(max/2.0);
            sum -= removed;
            maxheap.add(max - removed);
            k--;
        }
        
        
        return sum;
    }
}