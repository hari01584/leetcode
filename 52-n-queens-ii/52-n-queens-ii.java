/*
explanation: solving the problem by n queen method and backtracking. while using branch and bound to eliminate bad cases while looking for diagonal queens at 45 and 135 degrees.

testcase:
n = 4 -> Works

Time & Space Complexity: O(n^2) and O(n) respectively
Since backtracking loop takes n square steps and there is also checking inside backtracking which takes n2 steps therefore the total time complexity is O(n2) while since backtracking stacks are maintained therefore total space complexity is O(n)
*/

class Solution {
    List<List<String>> resu = new ArrayList<>();
    HashMap<Integer, Integer> config = new HashMap<>();
    int[] conf;
    
    public void checkAndAddValidBoard(int n){
        if(conf[n-1] == -1) return;
        char[] s = new char[n];
        Arrays.fill(s, '.');
        List<String> r = new ArrayList<>();
        for(int i=0; i<n; i++){
            s[conf[i]] = 'Q';
            String string = new String(s);
            r.add(string);
            s[conf[i]] = '.';
        }
        resu.add(new ArrayList<>(r));
        
    }
    
    public int invalidBoard(){
        HashMap<Integer, Integer> reversi = new HashMap<>();

        // Check if current board invalid
        for(int i=0; i<conf.length; i++){
            int e = conf[i];
            if(e==-1) break;
            
            if(reversi.containsKey(e)) return 1;
            reversi.put(e, i);
            
            // Diag checking 45 Upward
            int x = i-1;
            int y = e-1;
            while(x != -1 && y != -1){
                if(conf[x] == y) return 1;
                
                y = y-1;
                x = x-1;
            }
            
            // Diag checking 135 Upward
            x = i-1;
            y = e+1;
            while(x != -1 && y != -1 && y < conf.length){
                if(conf[x] == y) return 1;
                y = y+1;
                x = x-1;
            }

        }
        
        return 0;
    }
    
    public void backnqueen(int si, int sj, int n){
        if(invalidBoard() == 1) return;
        checkAndAddValidBoard(n);
        
        if(si > n){
            return;
        }
        
        System.out.println(si+" "+sj);
        System.out.println(Arrays.toString(conf));
        
        for(int j=sj; j<n; j++){
            // Optimize; Skip if j == sj
            conf[si] = j;
            backnqueen(si+1, sj, n);
            conf[si] = -1;
        }
    }
    public int totalNQueens(int n) {
        List<List<String>> r = new ArrayList<>();
        int c[] = new int[n+1];
        Arrays.fill(c, -1);
        
        conf = c;

        backnqueen(0, 0, n);
        
        return resu.size();
    }
}