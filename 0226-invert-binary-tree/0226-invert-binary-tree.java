/*
explanation: invert tree using swap! The program works by iterating over each node and swapping its left and right child members!

testcase: [4,2,7,1,3,6,9] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this algorithm is linear due to traversal of each node and space required for recursion!
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
    public void inorderswap(TreeNode root){
        if(root == null) return;
        
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        
        inorderswap(root.left);
        inorderswap(root.right);
    }
    
    
    public TreeNode invertTree(TreeNode root) {
        inorderswap(root);
        return root;
    }
}