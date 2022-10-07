/*
explanation: valid bst search by placing bounds on binary tree, it works by first checking if root val is between a set of allowed range of values and then checking for left and right subtree with adjusted new ranges.

testcase: [2, 1, 3] -> Works

Time & Space Complexity: O(n) & O(n): Time complexity is same as tree traversals!
*/

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

class Solution {
    public boolean isValidBSTRecur(TreeNode root, long lbound, long rbound) {
        if(root == null) return true;
        
        if(root.val <= lbound || root.val >= rbound) return false;
        
        return isValidBSTRecur(root.left, lbound, root.val) && isValidBSTRecur(root.right, root.val, rbound);
    }
    
    public boolean isValidBST(TreeNode root) {        
        return isValidBSTRecur(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}