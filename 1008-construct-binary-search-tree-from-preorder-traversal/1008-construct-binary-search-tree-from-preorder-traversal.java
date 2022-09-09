/*
explanation: constructing binary search tree from preorder traversal using the fact that any bst tree values if sorted becomes its inorder traversal, we can therefore use preorder and inorder traversal to find the given binary tree like any other approaches
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
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> inorderIndex = new HashMap();

    public TreeNode construct(int start, int end, int preindex){
        if(preindex>=preorder.length || preindex < 0 || start > end) return null;
        
        int h  = preorder[preindex];
        int inord = inorderIndex.get(h);
        
        int nprel = preindex+1;
        int nprer = preindex+inord+1-start;
        
        // System.out.println("L "+start+" "+end+" "+preindex+" "+h+" "+inord+" "+nprel+" "+nprer);
        
        TreeNode node = new TreeNode(h);
        node.left = construct(start, inord-1, nprel);
        node.right = construct(inord+1, end, nprer);
        
        return node;
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] sorted = preorder.clone();
        Arrays.sort(sorted);

        this.preorder = preorder;
        this.inorder = sorted;
        
        for(int i=0; i<inorder.length; i++){
            inorderIndex.put(inorder[i], i);
        }
        
        return construct(0, preorder.length-1, 0);
    }
}