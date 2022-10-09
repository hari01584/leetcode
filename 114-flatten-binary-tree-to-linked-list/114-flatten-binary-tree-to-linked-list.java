/*
explanation: flatten binary tree to linkedlist in constant space complexity! The algorithm works by first recursively iterating the binary tree in preorder way and then using a prev maintained pointer setting previous left to root! Finally this will give a complex temporary tree where using the fixskew method we can remove excess nodes and transfer the element to right linkedtree!

testcase: [1,2,5,3,4,null,6] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear since we use recursive iteration to loop over whole tree, also space complexity is constant because we are making modification on tree itself and not storing nodes in any data structures!
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
    TreeNode prev;
    public void recur(TreeNode root){
        if(root == null) return;
        
        // Do operations with prev and root and then set prev as = root
        if(prev != null){
            // System.out.println("root "+root.val+" prev "+prev.val);
            prev.left = root;
        }
        prev = root;
        
        recur(root.left);
        recur(root.right);
    }
    
    public void fixskew(TreeNode root){
        if(root == null) return;
        
        root.right = root.left;
        root.left = null;
        
        fixskew(root.right);
    }
    
    public void flatten(TreeNode root) {
        prev = null;
        recur(root);
        fixskew(root);
    }
}