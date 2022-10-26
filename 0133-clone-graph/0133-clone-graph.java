/*
explanation: cloning a graph by using hashmap and recursion! The program works by maintaining a mapping from unique node value to node, for each unvisited node, we clone it and check for its neighbour, now for neighbour there can be one of two cases: either the neighbour is cloned already previously (so its value is stored in mapping from that neighbour value -> neighbour node) or it is to be cloned for the first time, in case 1, we simply retrieve from map and add to new list of new neighbour, if not then it will recursively clone the neighbour and add it to list!

testcase: [[2,4],[1,3],[2,4],[1,3]] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear due to use of recursion and stack space needed for it!
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    Map<Integer, Node> map = new HashMap<>();
    
    public Node clone(Node node){
        // System.out.println("Cloning "+node.val);
        if(node == null) return null;
        Node root = new Node(node.val);
        map.put(node.val, root);
        List<Node> neighbors = new ArrayList<>();
        for(Node n : node.neighbors){
            if(map.containsKey(n.val)){
                // Already cloned, simply add
                neighbors.add(map.get(n.val));
            } else {
                // Create new, add to map
                neighbors.add(clone(n));
            }
        }
        root.neighbors = neighbors;
        return root;
    }
    
    public Node cloneGraph(Node node) {
        Node root = clone(node);
        return root;
    }
}