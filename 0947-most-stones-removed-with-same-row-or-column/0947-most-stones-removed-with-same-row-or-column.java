/*
explanation: most stoned removed using dfs + island finding approach! with 2d to 1d transformation! The program works by first converting the points (xi, yi) from 2d dimensions to 1d map from x1 -> y1+10000 and y1 + 10000 -> x1, basically it works by first finding isolated islands using three steps:
1. Building connected map
2. Looping over each stone coordinate and
3. Running dfs on each, marking them done and going to next node! for each unique node to run dfs, we increment island count!

Finally returning stones size - no.of.island, which gives our answer!

testcase: [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear since dfs with 1d optimization is used and a hashmap to store mapping is required!
*/
class Solution {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    
    public void mapInsert(int x, int y, boolean pad){
        if(pad) x+=10000;
        else y+= 10000;
        
        List<Integer> l = map.get(x);
        if(l == null) l = new ArrayList<>();
        l.add(y);
        
        map.put(x, l);
    }
    
    public void buildConnectedMap(int[][] stones){
        for(int[] p : stones){
            mapInsert(p[0], p[1], false);
            mapInsert(p[1], p[0], true);
        }
    }
    
    public int dfs(int node, HashMap<Integer, Boolean> visited){
        if(visited.getOrDefault(node, false)) return 0;
        visited.put(node, true);
        
        List<Integer> ls = map.get(node);
        int r = 0;
        for(Integer i : ls){
            r = Math.max(r, dfs(i, visited));
        }
        return r+1;
    }
    
    public int removeStones(int[][] stones) {
        buildConnectedMap(stones);
        int ans=0;
        HashMap<Integer, Boolean> visited = new HashMap<>();

        int island = 0;
        for(int[] pair : stones){
            int n = pair[0];
            if(!visited.containsKey(n)){
                island++;
                int tr = dfs(n, visited);
            }
        }
        
        return stones.length - island;
    }
}