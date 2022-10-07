/*
explanation: The program works by first iterating each node in bst and checking if it is valid bst, according to conditions of being bst or not it updates and returns a treeobject which stores many data according to particular node val.

testcase:
[1,4,3,2,4,2,5,null,null,null,null,null,null,4,6] -> Works

Time & Space Complexity: O(n) & O(n): Because for each time we visit node of tree, we also verify if it is bst and even if it isn't we do the same for all left and right subnodes.
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
    int gmax = 0;
    
    class TreeObject {
        int val;
        boolean isBst = false;
        int msum;
        int minvtree = Integer.MIN_VALUE;
        int maxvtree = Integer.MAX_VALUE;

        TreeObject(){};
        TreeObject(int val, boolean isBst, int msum){
            this.val = val;
            this.isBst = isBst;
            this.msum = msum;
        }
        
        TreeObject(int val, boolean isBst, int msum, int minvtree, int maxvtree){ 
            this.val = val;
            this.isBst = isBst;
            this.msum = msum;
            this.minvtree = minvtree;
            this.maxvtree = maxvtree;
        }
        
        @Override
        public String toString(){
            return val+" "+isBst+" "+msum+", ";
        }
    }

    public TreeObject isValidBst(TreeNode root){
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            return new TreeObject(root.val, true, root.val, root.val, root.val);
        }
        
        TreeObject s1 = isValidBst(root.left);
        TreeObject s2 = isValidBst(root.right);
        
        TreeObject r = null;
        
        if(s1 != null && s2 != null){
            if(root.val > s1.maxvtree && s1.isBst && root.val < s2.minvtree && s2.isBst){
                r = new TreeObject(root.val, true, root.val+s1.msum+s2.msum, Math.min(root.val, s1.minvtree), Math.max(root.val, s2.maxvtree));
            }
            else {
                r = new TreeObject(root.val, false, Math.max(s1.msum, s2.msum));
            }
        } else if(s1 == null){
            if(root.val < s2.minvtree && s2.isBst){
                r = new TreeObject(root.val, true, root.val+s2.msum, Math.min(root.val, s2.minvtree), Math.max(root.val, s2.maxvtree));
            } else {
                r = new TreeObject(root.val, false, s2.msum);
            }
        } else {
            if(root.val > s1.maxvtree && s1.isBst){
                r = new TreeObject(root.val, true, root.val+s1.msum, Math.min(root.val, s1.minvtree), Math.max(root.val, s1.maxvtree));
            } else {
                r = new TreeObject(root.val, false, s1.msum);
            }
        }
        
        gmax = Math.max(gmax, r.msum);
        System.out.println(root.val+" => "+s1+" ; "+s2+" R: "+r);

        return r;
    }
    
    public int maxSumBST(TreeNode root) {
        TreeObject t = isValidBst(root);
        
        return Math.max(0, gmax);
    }
}