/*
explanation: finding path exist by recursive dfs! The algorithm works ny first building adjancent edge map and then using it to traverse each node one by one and marking them! Doing so it reaches to the destination nodes and if true returns it!

testcase: [[0,1],[1,2],[2,0]] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of program is linear since single loop and recursion is used, also space complexity is linear due to space required by map to maintain edges!
*/
class Solution {
    HashMap<Integer, List<Integer>> edgeMap = new HashMap<>();
    int[] marked;
    
    public void addDirectionalEdge(int s, int d){
        List<Integer> edg = edgeMap.getOrDefault(s, new ArrayList<>());
        edg.add(d);
        edgeMap.put(s, edg);
    }
    
    public boolean find(int s, int dest){
        if(s == dest) return true;
        if(marked[s] == 1) return false;
        marked[s] = 1;
        
        List<Integer> edg = edgeMap.getOrDefault(s, new ArrayList<>());
        for(Integer ig : edg){
            if(find(ig, dest)) return true;
        }
        
        return false;
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {      
        marked = new int[n];
        for(int[] p : edges){
            addDirectionalEdge(p[0], p[1]);
            addDirectionalEdge(p[1], p[0]);
        }
        
        return find(source, destination);
    }
}