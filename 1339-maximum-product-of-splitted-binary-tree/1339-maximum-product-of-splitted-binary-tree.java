/*
explanation: maximum product by using sum and tree traversal! The program works by first calculating the total sum of all nodes in tree and then using another traversal to find sum till root node and subtracting it from total sum, at every level traversal we consider if that specific node is taken away and as such if that's the case then the sum of first part will be sum till that node, while sum of another part will be max-sum, multiplying these two and maximizing it will give our answer!

testcase: [1,2,3,4,5,6] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear, since we use recursion two times therefore time complexity is linear, also the stack space used to store recursive calls will make space complexity linear as well!
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
    long msum = Long.MIN_VALUE;
    long findMaxSum(TreeNode root){
        if(root == null) return 0;
        return root.val + findMaxSum(root.left) + findMaxSum(root.right);
    }
    
    int maximizeSum(TreeNode root, long maxsum){
        if(root == null) return 0;
        int sum = root.val + maximizeSum(root.left, maxsum) + maximizeSum(root.right, maxsum);
        
        long prodc = sum * (maxsum-sum);
        // System.out.println("With node "+root.val+", sum="+sum+", prod="+prodc);
        msum = Math.max(msum, prodc);
        
        return sum;
    }
    
    public int maxProduct(TreeNode root) {
        long maxsum = findMaxSum(root);
        
        maximizeSum(root, maxsum);
        return (int)(msum%(1e9+7));
    }
}