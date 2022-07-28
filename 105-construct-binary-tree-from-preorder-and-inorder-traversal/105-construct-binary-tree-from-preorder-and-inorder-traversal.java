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
explanation: constructing binary tree by the method of recursion, it works by first choosing a root node from preorder array and then finding this in inorder array, now all the nodes left to this will be contained in its left subpartition, and right will be in right subpartition, so using this we can create a binary tree recursively which automatically predicts next head node and create structure from inorder

testcase:
[3,9,20,15,7]
[9,3,15,20,7] -> Works

Time & Space Complexity: O(n) & O(n): Since we iterate over each elements and have a hashmap that stores all the nodes therefore time and space complexity is linear.
*/
class Solution {
    HashMap<Integer, Integer> elementToIndex = new HashMap<>();
    int[] pre;
    int[] inor;
    int index = 0;
    public TreeNode buildIt(int start, int end, int index){
        if(index >= pre.length || index < 0 || start > end) return null;
        
        // Get element to find from preorder (ie root)
        TreeNode root = new TreeNode(pre[index]);
        
        // System.out.println(start+" "+end+" "+index+" "+root.val);
        
        // Find this element pos in inorder
        int idx = elementToIndex.get(root.val);
        TreeNode left = buildIt(start, idx-1, index+1);
        TreeNode right = buildIt(idx+1, end, index + idx - start + 1);
        
        root.left = left;
        root.right = right;
        
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0; i<inorder.length; i++){
            elementToIndex.put(inorder[i], i);
        }
        pre = preorder;
        inor = inorder;
        
        TreeNode val = buildIt(0, inorder.length-1, 0);
        
        return val;
    }
}