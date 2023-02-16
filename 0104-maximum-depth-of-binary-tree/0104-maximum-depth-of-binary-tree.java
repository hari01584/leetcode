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
explanation: max depth finder using recursion + maintaining levels with a global max integer to store the maximum of all levels recorded so far!

testcase: [3,9,20,null,null,15,7] -> Works

Time & Space Complexity: O(n) & O(n): Since recursion is used!
*/
class Solution {
    int mdepth = 0;
    
    void maxdepth(TreeNode root, int level){
        if(root==null) return;
        mdepth = Math.max(mdepth, level);
        
        maxdepth(root.left, level+1);
        maxdepth(root.right, level+1);
    }
    public int maxDepth(TreeNode root) {
        maxdepth(root, 1);
        return mdepth;
    }
}