/*
explanation: Max sum of subsequence using segment trees, this solution using these trees to make incremental query updates to our builded seg trees, It can be shown that for each node there could be 4 states, ie is left
one is included, right one is included/excluded.. We combine nodes by having different cases, for example to combine two child nodes, we will take their states such that the left child node rightmost and right child mode leftest cannot be taken simultaneously, Using such there are 4 different config.

Time & Space Complexity: O(n + qlogn) & O(n): Since segment tree build takes linear time and space, while also each query updation takes logn time, therefore its time and space complexity are n+qlogn and n respectively.
*/
class SegmentTreeNode {
    int[][] s;
    int l;
    int r;
    SegmentTreeNode leftNode;
    SegmentTreeNode rightNode;
    
    SegmentTreeNode(int l, int r) {
        s = new int[2][2];
        this.l = l;
        this.r = r;
        leftNode = null;
        rightNode = null;
    }
    
    String getObjectiveString() {
        return String.format("[00]=%s, [01]=%s, [10]=%s, [11]=%s", s[0][0], s[0][1], s[1][0], s[1][1]);
    }
    
    @Override
    public String toString() {
        String lnodestr = "";
        String rnodestr = "";
        if (leftNode != null) {
            lnodestr = leftNode.toString();
        }
        if (rightNode != null) {
            rnodestr = rightNode.toString();
        }
        String mystr = String.format("l: %s, r: %s, obj: <%s>, {lNode: %s, rNode: %s}", l, r, getObjectiveString(), lnodestr, rnodestr);

        return mystr;
    }
}

class Solution {
    long MODULO = (long)(1e9 + 7);

    int max(long a, long b, long c) {
        return (int)(Math.max(a, Math.max(b, c)) % MODULO);
    }

    void setObjectives(SegmentTreeNode n, SegmentTreeNode l, SegmentTreeNode r) {
        // Use child l and child r nodes to set objectives to current node :)
        n.s[1][1] = max(
            l.s[1][0] + r.s[0][1],
            l.s[1][1] + r.s[0][1],
            l.s[1][0] + r.s[1][1]
        );
        
        n.s[0][1] = max(
            l.s[0][0] + r.s[1][1],
            l.s[0][0] + r.s[0][1],
            l.s[0][1] + r.s[0][1]
        );
        
        n.s[1][0] = max(
            l.s[1][0] + r.s[0][0],
            l.s[1][0] + r.s[1][0],
            l.s[1][1] + r.s[0][0]
        );
        
        n.s[0][0] = max(
            l.s[0][0] + r.s[0][0],
            l.s[0][1] + r.s[0][0],
            l.s[0][0] + r.s[1][0]
        );
    }
    
    SegmentTreeNode buildTree(int l, int r, int[] nums) {
        if (l == r) {
            // Single tree node, simple give default one
            SegmentTreeNode node = new SegmentTreeNode(l, r);
            node.s[0][0] = 0;
            node.s[1][1] = nums[l];
            node.s[0][1] = -1000000;
            node.s[1][0] = -1000000;
            return node;
        }
        
        SegmentTreeNode leftNode = buildTree(l, (l+r)/2, nums);
        SegmentTreeNode rightNode = buildTree(((l+r)/2)+1, r, nums);

        SegmentTreeNode node = new SegmentTreeNode(l, r);
        node.leftNode = leftNode;
        node.rightNode = rightNode;
        setObjectives(node, leftNode, rightNode);
        
        return node;
    }
    
    void incrementalUpdate(SegmentTreeNode root, int pos, int value) {
        if (root.l == root.r && root.r == pos) {
            // We reach the culprit, the rootest node :)
            root.s[1][1] = value; // Set value thus!
            return;
        }
                
        // Find which node should we go to
        if (pos >= root.leftNode.l && pos <= root.leftNode.r) {
            // Go left :)
            incrementalUpdate(root.leftNode, pos, value);
        } else {
            incrementalUpdate(root.rightNode, pos, value);
        }
        
        // At the end, we assume things are updated, so simply update root value!
        setObjectives(root, root.leftNode, root.rightNode);
    }
    
    public int getMaxObjective(SegmentTreeNode n) {
        return Math.max(
            Math.max(n.s[0][0], n.s[0][1]),
            Math.max(n.s[1][0], n.s[1][1])
        );
    }

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        SegmentTreeNode root = buildTree(0, nums.length-1, nums);
        // System.out.println(root);

        long ans = 0;
        // Now for each query, we even have to update things up :)
        for (int[] q : queries) {
            incrementalUpdate(root, q[0], q[1]);
            ans += getMaxObjective(root) % MODULO;
        }
        
        return (int)(ans % MODULO);
    }
}