/*
explanation: binary tree pruning based on recursive approach! The program works by first recursing all the trees and checking each node for the validity of left and right subtrees, if invalid (to be removed) node is found then it uses parent node parameter and a span representing direction of travel! in the end it checks for validity of root node and returns the pruned tree!

testcase:
[1,null,0,0,1] -> Works

Time & Space Complexity: O(n) & O(n): Since we only do binary tree traversal therefore time complexity is linear!
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
    public boolean markBad(TreeNode root, TreeNode parent, int span){
        if(root == null) return true;
        
        boolean lbad = markBad(root.left, root, -1);
        boolean rbad = markBad(root.right, root, +1);
        
        if(lbad && rbad && root.val==0){
            // root.val = -1;
            if(span == -1) parent.left = null;
            else if(span == +1) parent.right = null;
            return true;
        }
        return false;
    }
    
    public TreeNode pruneTree(TreeNode root) {
        markBad(root, null, 0);
        if(root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }
}