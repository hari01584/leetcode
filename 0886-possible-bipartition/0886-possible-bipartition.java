class Solution {
    class DSU{
        int n;
        int[] arr;
        DSU(int n){
            this.n = n;
            arr = new int[n];
            
            for(int i=0; i<n; i++){
                arr[i] = i;
            }
        }
        
        void union(int x, int y){
            int h1 = find(x);
            int h2 = find(y);
            
            if(h1 == h2) return;
            if(x == h1){
                arr[x] = h2;
            } else if(y == h2){
                arr[y] = h1;
            } else {
                arr[h1] = h2;
            }
        }
        
        int find(int x){
            while(arr[x] != x){
                x = arr[x];
            }
            return x;
        }
    }

    HashMap<Integer, List<Integer>> map = new HashMap<>();
    void addPair(int x, int y){
        List<Integer> lst = map.getOrDefault(x, new ArrayList<>());
        lst.add(y);
        map.put(x, lst);
    }


    public boolean possibleBipartition(int n, int[][] dislikes) {
        int persons = n+1;
        
        DSU dsu = new DSU(persons);
        
        for(int[] p : dislikes){
            addPair(p[0], p[1]);
            addPair(p[1], p[0]);
        }
        
        // dsu.union(1, red); // Set 1 to red and now start!
        for(int i=1; i<persons; i++){
            // Color current to something
            List<Integer> neigh = map.getOrDefault(i, new ArrayList<>());
            for(int iz=0; iz<neigh.size(); iz++){
                int nval = neigh.get(iz);
                if(dsu.find(nval) == dsu.find(i)) return false;
                dsu.union(nval, neigh.get(0));
            }
        }
        
        return true;
    }
}