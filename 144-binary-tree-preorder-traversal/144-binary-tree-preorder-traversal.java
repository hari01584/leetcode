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
explanation: Morris preorder traversal! tree traversal in constant space without recursions or stacks! It works by having one node backpoint to the parent node and using it to recall the current pointer back to the parent of tree!

testcase: [1,null,2,3]

Time & Space Complexity: O(n) & O(n): time and space complexity is linear in morris traversal algorithms!
*/
class Solution {
    
    TreeNode getLinkNode(TreeNode root){
        TreeNode left = root.left;
        if(left==null) return null;
        while(left.right != null && left.right!=root) left=left.right;
        return left;
    }
    
    public void logNode(TreeNode t){
        System.out.println("c: "+t.val+" l: "+t.left+" r:"+t.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        
        TreeNode current = root;
        while(current != null){
            // System.out.println(current.val);
            if(current.left == null){
                elements.add(current.val);
                current = current.right;
            } else {
                TreeNode link = getLinkNode(current);
                // System.out.println(current.val+" -> "+link.val);
                if(link.right == current){
                    // System.out.println("reached again "+current.val);
                    
                    // Reset link
                    link.right = null;
                    
                    // go right subtree
                    current = current.right;
                } else {
                    link.right = current; // Create backlink here :)
                    // System.out.println("Created backlink "+link.val+" r="+current.val);
                    elements.add(current.val);
                    current = current.left; // Change current to left subtree
                }
            }
        }
        
        return elements;
    }
}