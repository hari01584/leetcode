// { Driver Code Starts
// Initial Template for Java

import java.util.*;
class Rat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) a[i][j] = sc.nextInt();

            Solution obj = new Solution();
            ArrayList<String> res = obj.findPath(a, n);
            Collections.sort(res);
            if (res.size() > 0) {
                for (int i = 0; i < res.size(); i++)
                    System.out.print(res.get(i) + " ");
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}
// } Driver Code Ends


// User function Template for Java

/*
explanation: Rat path finding algorithm using backtracking and safe function.

testcase: -> All works

Time & Space Complexity: O(3^n), since at any time mouse has 3 options to choose from and there are total m empty boxes therefore time complexity is O(3^n), also space complexity is O(n) for storing stacks of backtracking.
*/
// User function Template for Java

// m is the given matrix and n is the order of matrix
class Solution {
    int cx=0;
    int cy=0;
    int[][] board;
    int n;
    
    ArrayList<Integer> path = new ArrayList<>();
    
    ArrayList<Integer> direclist = new ArrayList<>();

    ArrayList<ArrayList<Integer>> soln = new ArrayList<>();

    boolean finish = false;
    
    public int pack(int a, int b){
        int p = (0xf & a)<<4 | (0xf & b);        
        return p;
    }
    public void unpack(int u){
        int a = (u >> 4) & 0xf;
        int b = (u) & 0xf;
    }
    
    public boolean isSafe(int x, int y){
        if(path.contains(pack(x,y))){
            return false;
        }
        
        if(x < 0 || x >= n || y < 0 || y >= n){
            return false;
        }
        if(board[y][x] == 0) return false;
        
        // Additional logic to check answer
        // if(x == n-1 && y == n - 1){
        //     finish = true;
        // }
        return true;
    }
    
    public int transfX(int x, int y, int direc){
        if(direc == 0) return x + 1;
        else if(direc == 2) return x - 1;
        return x;
    }
    
    public int transfY(int x, int y, int direc){
        if(direc == 1) return y + 1;
        else if(direc == 3) return y - 1;
        return y;
    }
    
    public int transf(int cx, int cy, int direc){
        int u = path.get(path.size()-1);
        int x = (u >> 4) & 0xf;
        int y = (u) & 0xf;

        if(direc == 0) x = x + 1;
        else if(direc == 1) y = y + 1;
        else if(direc == 2) x = x - 1;
        else if(direc == 3) y = y - 1;
        
        return pack(x, y);
    }
    
    public int inverse(int direc){
        if(direc == 0) return 2;
        if(direc == 1) return 3;
        if(direc == 2) return 0;
        if(direc == 3) return 1;
        return -1;
    }
    
    int step = 20;
    public void backRat(int prevdirec){
        int up = path.get(path.size()-1);
        int cx = (up >> 4) & 0xf;
        int cy = (up) & 0xf;

        if(cx == n-1 && cy == n-1){
            soln.add(new ArrayList<>(direclist));
            // System.out.println("Found solution");
            return;
        }
        
        // if(step > 0){
        //     step--;
        // }
        // else return;
        // System.out.println(cx+" "+cy);

        for(int direc=0; direc<4; direc++){
            if(direc == prevdirec) continue;
            
            int u = transf(cx, cy, direc);
            
            int x = (u >> 4) & 0xf;
            int y = (u) & 0xf;
      
            // System.out.println("E "+x+" "+y);
                    
            if(isSafe(x, y)){
                // System.out.println("S "+x+" "+y);

                path.add(pack(x, y));
                direclist.add(direc);
                backRat(inverse(direc));
                direclist.remove(direclist.size()-1);
                path.remove(path.size()-1);
            }
        }
    }
    
    public String resolvetopath(List<Integer> p){
        String path = "";
        for(Integer i : p){
            if(i==0) path += "R";
            if(i==1) path += "D";
            if(i==2) path += "L";
            if(i==3) path += "U";
        }
        return path;
    }
    
    public ArrayList<String> findPath(int[][] m, int nv) {
        if(m[0][0] == 0) return new ArrayList<>();
        board = m;
        n = nv;
        path.add(pack(0, 0));
        ArrayList<String> r = new ArrayList<>();
        
        backRat(3);
        
        for(List<Integer> p : soln){
            String s = resolvetopath(p);
            r.add(s);
        }
        
        return r;
    }
}