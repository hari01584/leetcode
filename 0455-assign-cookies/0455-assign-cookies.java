/*
explanation: assign cookie using greedy priority queue algorithm! The program works by maintaining two priority queue to get
childs and cookies corresponding to them, we can use greedy here because of the fact that how a child with small greed can be
satisfied with both small and large cookie, however in case of feeding them the larger cookie, we might make it such that another child would not get cookie and thus it might not be optimum, conversely, the optimized value or best possible allocation will be when each child will get cookie that is just greater than their hunger.

testcase: g = [1,2,3], s = [1,1] -> Works

Time & Space Complexity: O(n)& O(n): Time complexity is linear due to how we iterate over each child and cookie only once, also
space complexity is linear due to maintaining priorityqueues.
*/
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        PriorityQueue<Integer> childs = new PriorityQueue<Integer>();
        PriorityQueue<Integer> cookies = new PriorityQueue<Integer>();
        
        for (int i=0; i<g.length; i++) {
            childs.add(g[i]);
        }
        
        for (int i=0; i<s.length; i++) {
            cookies.add(s[i]);
        }
        
        int ans = 0;

        while (childs.size() > 0) {
            // Get each child, and cookie corresponding to them!
            int child_greed = childs.poll();

            // Find a cookie, which this child can have, ie the cookie must be greater than or equal to his greed!
            while (cookies.size() > 0) {
                int cookie_size = cookies.poll();
                // System.out.println("For child greed, "+child_greed+" mycookie: "+cookie_size);
                if (cookie_size >= child_greed) {
                    ans++;
                     break;
                }
                // Good case!
            }
        }
        
        return ans;
    }
}