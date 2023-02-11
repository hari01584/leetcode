

class Solution {
    class C {
        int rcost;
        int bcost;
        
        C(int r, int b){
            rcost = r;
            bcost = b;
        }
        
        @Override
        public String toString(){
            return rcost+" - "+bcost;
        }
    }
    
    class E {
        int t;
        boolean isRed;
        
        E(int t, boolean isRed){
            this.t = t;
            this.isRed = isRed;
        }
        
        @Override
        public String toString(){
            return t+" "+isRed;
        }
    }
    
    C[] cost;
    HashMap<Integer, List<E>> edges = new HashMap<>();
    public void addMap(int a, E b){
        List<E> lst = edges.getOrDefault(a, new ArrayList<E>());
        lst.add(b);
        edges.put(a, lst);
    }
    
    public void dfs(int n, boolean isLastNodeRed, int cst){
        // System.out.println("Calling "+n+" lred?:"+isLastNodeRed+" cost:"+cst);
        
        if(isLastNodeRed && cost[n].rcost < cst) return; // Reached here by rednode, and curr cost to reach > prev saved cost!
        else if(!isLastNodeRed && cost[n].bcost < cst) return; // Reached here by blue node, and curr cost blue > prev saved!
        
        List<E> edge = edges.getOrDefault(n, new ArrayList<>());
        for(E edg : edge){
            // System.out.println("Probing "+edg);
            if(isLastNodeRed == edg.isRed) continue; // Same type of node, ie coming from red and still red, or coming from blue and still blue :(
            // System.out.println("Setting mincost for "+edg+" ");
            if(edg.isRed){
                cost[edg.t].rcost = Math.min(cost[edg.t].rcost, 1+cst);
            } else {
                cost[edg.t].bcost = Math.min(cost[edg.t].bcost, 1+cst);
            }
        }
        
        // Loop over now bfs
        for(E edg : edge){
            if(isLastNodeRed == edg.isRed) continue; // Same type of node, ie coming from red and still red, or coming from blue and still blue :(
            dfs(edg.t, !isLastNodeRed, 1+cst);
        }
    }
    
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        cost = new C[n];
        for(int i=0; i<n; i++){
            cost[i] = new C(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        
        for(int[] r : redEdges){
            addMap(r[0], new E(r[1], true));
        }
        
        for(int[] b : blueEdges){
            addMap(b[0], new E(b[1], false));
        }
        
        dfs(0, true, 0);
        dfs(0, false, 0);
        
        // System.out.println(Arrays.toString(cost));
        int[] ans = new int[n];
        ans[0] = 0;
        for(int i=1; i<n; i++){
            C cst = cost[i];
            int mcost = Math.min(cst.rcost, cst.bcost);
            if(mcost == Integer.MAX_VALUE) ans[i] = -1;
            else ans[i] = mcost;
        }
        // look inji
        
        
        return ans;
    }
}