/*
explanation: possible bipartition using backtracking and checking valid state method! The program works by building all states of possible groups, we define two groups that is 0 and 1 and using backtracking assign each element to one of these 2 and check for its validity! If any valid groups are found then we return that it is possible to group the elements!

testcase: [[1,2],[1,3],[2,4]] -> Works

Time & Space Complexity: O(n^2) & O(n): Time and space complexity of this program is linear because it takes n iterations with n iterations in checking for validity, also space linear due to list required to maintain groupings!
*/
class Solution {
    int[][] adj;
    int max_person;
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    
    void setAdjacency(int x, int y){
        adj[x][y] = 1;
        adj[y][x] = 1;
    }
    
    boolean checkCollisonAndAdd(int e, HashMap<Integer, Integer> mp){
        // System.out.println(Arrays.toString(lst.toArray()));
        // for(Integer i : lst){
        //     if(adj[i][e] == 1) return false;
        // }
        List<Integer> coll = map.getOrDefault(e, new ArrayList<>());
        for(Integer i : coll){
            if(mp.containsKey(i)) return false;
        }
        mp.put(e, 1);
        return true;
    }
    
    boolean checkValid(int[] arr){
        HashMap<Integer, Integer> l1 = new HashMap<>();
        HashMap<Integer, Integer> l2 = new HashMap<>();
        
        for(int i=1; i<max_person; i++){
            if(arr[i] == -1) return false; // First hurde, all element set or not completed!
            
            if(arr[i] == 0){
                // 0th group, check collison before adding!
                if(!checkCollisonAndAdd(i, l1)) return false;
            } else {
                if(!checkCollisonAndAdd(i, l2)) return false;
            }
        }
        
        return true;
    }
    
    void addPair(int x, int y){
        List<Integer> lst = map.getOrDefault(x, new ArrayList<>());
        lst.add(y);
        map.put(x, lst);
        
        // Add reverse pair too
        // addPair(y, x);
    }
    
    public boolean crawl(int n, int[] arr){
        if(n >= max_person){
            if(checkValid(arr)){
                // System.out.println("Valid arr found, hurray!");
                // System.out.println(Arrays.toString(arr));
                return true;
            }
            return false;
        }
        
        // System.out.println("Crawling to "+n+" with arr :");
        // System.out.println(Arrays.toString(arr));
        
        for(int i=n; i<max_person; i++){
            for(int v=0; v<=1; v++){
                arr[i] = v;
                if(crawl(n+1, arr)) return true;
                arr[i] = -1;
            }
        }
        
        return false;
    }
    
    // -1 = White
    // 0 = Red
    // 1 = Blue
    int[] visited;
    
    public boolean dfs(int n, int color, int[] colors){
        if(visited[n] == 1) return true;
        visited[n] = 1;
        
        // System.out.println("Coloring: "+n);
        colors[n] = color; // Set color to current one and all adjancent to nexts!
        List<Integer> coll = map.getOrDefault(n, new ArrayList<>());
        for(Integer i : coll){
            // System.out.println("Setting  color, for adjacent of "+n+" which is! "+i+" to color: "+(1-color));
            if(colors[n] == colors[i]){
                // Wrong, break!
                // System.out.println("Wrong break!");
                return false;
            }
            if(!dfs(i, 1-color, colors)) return false;;
        }
        
        return true;
    }
    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        max_person = n+1;
        adj = new int[n+1][n+1];
        // Build adjacentcy cache!
        for(int[] p : dislikes){
            setAdjacency(p[0], p[1]);
            addPair(p[0], p[1]);
            addPair(p[1], p[0]);
        }
        
        int[] arr = new int[max_person];
        Arrays.fill(arr, -1);
        
        visited = new int[max_person];
        
        // System.out.println(Arrays.toString(arr));
        
        // System.out.println(Arrays.toString(arr));
        for(int i=1; i<max_person; i++){
            if(!dfs(i, 0, arr)) return false;
        }
        
        return true;
    }
}