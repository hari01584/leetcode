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
explanation: max width finder by using the concepts of assigning numbers to each node! for each child node the number n is 2n+1 and 2n+2 respectively for left and right nodes! at the end of the value we simply get the max difference.

testcase: [1,3,2,5,3,null,9] -> Works

Time & Space Complexity: O(n) & O(1): Since all nodes are iterated therefore time complexity is linear, also since two queues are maintained with clearing nodes therefore space complexity is constant.

*/
class Solution {
    
    class Element{
        TreeNode node;
        int id;
        public Element(TreeNode n, int i){
            node = n;
            id = i;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        
        Queue<Element> current = new LinkedList<>();

        current.offer(new Element(root, 0));
        
        int ans = Integer.MIN_VALUE;

        while(!current.isEmpty()){
            int levelMin = Integer.MAX_VALUE;

            Queue<Element> nextree = new LinkedList<>();

            while(!current.isEmpty()){
                Element n = current.poll();
                // System.out.println(n.node.val + " " + n.id);
                levelMin = Math.min(levelMin, n.id);
                
                ans = Math.max(ans, n.id-levelMin+1);

                int lsnode = (2 * (n.id-levelMin)) + 1;
                int rtnode = (2 * (n.id-levelMin)) + 2;

                if(n.node.left != null){
                    nextree.add(new Element(n.node.left, lsnode));
                }
                if(n.node.right != null){
                    nextree.add(new Element(n.node.right, rtnode));
                }
            }
            
            current = new LinkedList<>(nextree);
        }
        
        return ans;
    }
}