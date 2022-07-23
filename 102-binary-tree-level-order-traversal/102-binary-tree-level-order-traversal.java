/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
explanation: level order traversal using two queues! It works by maintaining a current queue and next level queue system! for each level we iterate over elements and store all the child in temp queue, at the end of finishing we copy the temp queue back to our current queue and loop over all again!

testcase: [3,9,20,null,null,15,7] -> Works
Initially the current queue is initialized with root node, the next while loop polls over (removes) each element and add the child nodes to temp queue, therefore when element is 3 then its child 9 & 20 is added to temp queue, since 3 is last element the inner loop breaks and we copy temp (ie node 9 and 20 to current)

in next loop we iterate over current again with elements 9 and 20, and thus adds 15 and 7 to temp, at the end similiarly we copy 15 and 7 to current and discard 9 and 20.

Time & Space Complexity: O(n) & O(1): Time complexity is linear since all nodes is to be iterated ones, also space complexity is constant because for any level we only maintain two levels and discard all the other values in binary tree.
*/
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> current = new LinkedList<>();
        current.offer(root);
        
        while(!current.isEmpty()){
            Queue<TreeNode> temp = new LinkedList<>();
            List<Integer> lst = new ArrayList<>();
            while(!current.isEmpty()){
                TreeNode e = current.poll();
                lst.add(e.val);
                
                if(e.left != null) temp.offer(e.left);
                if(e.right != null) temp.offer(e.right);
            }
            ans.add(lst);
            current = new LinkedList<>(temp);
        }
        
        return ans;
    }
}