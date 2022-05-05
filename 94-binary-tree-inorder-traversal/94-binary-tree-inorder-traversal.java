/*
explanation: inorder traversal of binary tree using recursive approach. the recursive function calls left subtree, adds to list and finally right subtree and returns this in final function.

testcase:
[1,null,2,3] -> Works

Time and Space Complexity: O(n) and O(n)
Since a linear scan is required therefore time complexity is O(n) and since recursion of depth n is needed therefore space complexity is O(n).
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
    List<Integer> arr = new ArrayList<Integer>();
    public void inorder(TreeNode t){
        if(t == null) return;
        if(t.left != null) inorder(t.left);
        arr.add(t.val);
        if(t.right != null) inorder(t.right);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return arr;
    }
}