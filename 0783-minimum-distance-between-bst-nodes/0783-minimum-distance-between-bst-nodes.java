/*
explanation: min distance using minimization of difference between current root val and largest of left node, and smallest of right node to current root val, using this algorithm since this is bst, we can find minimum difference!

testcase:
[4,2,6,1,3] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this algorithm is linear, this is due to iterating over each node, also space complexity linear due to recursion and stacks!
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
    class R {
        int cur;
        int small;
        int largest;
        
        R(int c, int s, int l){
            cur = c;
            small = s;
            largest = l;
        }
        
        public String toString(){
            return cur + " -> " + small +", "+largest;
        }
    }
    
    int mindif = Integer.MAX_VALUE;
    
    R recurdis(TreeNode root){
        if(root == null) return new R(-1, 1000000, -1000000);
        
        // If go left then change l largest
        R left = recurdis(root.left);
        R right = recurdis(root.right);
        
        R ans = new R(root.val, 1000000, -1000000);
        ans.small = Math.min(ans.small, Math.min(root.val, left.small));
        ans.largest = Math.max(ans.largest, Math.max(root.val, right.largest));
        
        mindif = Math.min(mindif, Math.min(root.val - left.largest, right.small - root.val));
        
        // System.out.println("At "+root.val+" return: "+ans);
        
        return ans;
    }
    
    public int minDiffInBST(TreeNode root) {
        recurdis(root);

        return mindif;
    }
}