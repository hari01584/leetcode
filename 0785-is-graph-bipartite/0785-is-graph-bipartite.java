/*
explanation: graph coloring bipartite problem using dfs, the algo works by progressively using partition function to add
elements in hashset and mark its neighbours for inverse color. Progressively building this hashset over whole graph will
give our answer! Also in case when next node is to colored in a color that is already colored by opposite ones, then our
algorithm fails and we return false!

testcase: [[1,2,3],[0,2],[0,1,3],[0,2]] -> Works

Time & Space Complexity: O(V + E) & O(V + E): Time and space of this algorithm is V + E due to graph traversal algorithm
used.
*/
class Solution {
    public boolean partiteAdd(int cnode, boolean isRed, HashSet<Integer> red, HashSet<Integer> blue, int[][] graph) {
        // This alternates between red and blue and assign to each elements!
        // First check, if this cnode is already in set... if it is in correct set then return true!
        if (red.contains(cnode) && isRed) {
            // Placed good place, let's go
            return true;
        }
        
        if (blue.contains(cnode) && !isRed) {
            // Placed blue good, let's go
            return true;
        }
        
        // Bad cases when a node is found in bad place
        if (red.contains(cnode) && !isRed) {
            // Red has this already, but blue is what this is
            return false;
        }
        
        if (blue.contains(cnode) && !isRed) {
            // Vice versa of prev case
            return false;
        }
        
        // Now add element to set!
        if (isRed) {
            // element red, so add in red set!
            red.add(cnode);
        } else {
            blue.add(cnode);
        }

        // Use dfs to color or partition next element
        // Get list of elements using adjacency!
        int[] neighbours = graph[cnode];
        // Check for each element in list, color it with opposite color!
        for (int a = 0; a<neighbours.length; a++) {
            int nn = neighbours[a];
            if (!(partiteAdd(nn, !isRed, red, blue, graph))) {
                return false;
            }
        }
        
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        HashMap<Integer, List<Integer>> adjaceList = new HashMap<>();

        HashSet<Integer> red = new HashSet<Integer>();
        HashSet<Integer> blue = new HashSet<Integer>();

        // Now list is build, use this to color each node!
        for (int i=0; i<graph.length; i++) {
            int element = i;
            // Check if this element already in set, if it is then skip as it is previously colored somewhere
            if (red.contains(element) || blue.contains(element))
                continue;
            
            // Start coloring and finding for next node
            if (!partiteAdd(element, true, red, blue, graph))
                return false;
        }

        return true;
    }
}