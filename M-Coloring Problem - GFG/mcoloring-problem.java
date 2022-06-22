// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tc = scan.nextInt();

        while (tc-- > 0) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int E = scan.nextInt();

            boolean graph[][] = new boolean[N][N];

            for (int i = 0; i < E; i++) {
                int u = scan.nextInt() - 1;
                int v = scan.nextInt() - 1;
                graph[u][v] = true;
                graph[v][u] = true;
            }

            System.out.println(new solve().graphColoring(graph, M, N) ? 1 : 0);
        }
    }
}
// } Driver Code Ends

/*
Copied paste code to check the validity of gfg answers on gfg IDE, self-attempted answer was giving TLE.
*/
class solve {
    int V = 4;
    int color[];
 
    /* A utility function to check
       if the current color assignment
       is safe for vertex v */
    boolean isSafe(
        int v, int graph[][], int color[],
        int c)
    {
        for (int i = 0; i < V; i++)
            if (
                graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }
 
    /* A recursive utility function
       to solve m coloring  problem */
    boolean graphColoringUtil(
        int graph[][], int m,
        int color[], int v)
    {
        /* base case: If all vertices are
           assigned a color then return true */
        if (v == V)
            return true;
 
        /* Consider this vertex v and try
           different colors */
        for (int c = 1; c <= m; c++)
        {
            /* Check if assignment of color c to v
               is fine*/
            if (isSafe(v, graph, color, c))
            {
                color[v] = c;
 
                /* recur to assign colors to rest
                   of the vertices */
                if (
                    graphColoringUtil(
                        graph, m,
                        color, v + 1))
                    return true;
 
                /* If assigning color c doesn't lead
                   to a solution then remove it */
                color[v] = 0;
            }
        }
 
        /* If no color can be assigned to
           this vertex then return false */
        return false;
    }
 
    /* This function solves the m Coloring problem using
       Backtracking. It mainly uses graphColoringUtil()
       to solve the problem. It returns false if the m
       colors cannot be assigned, otherwise return true
       and  prints assignments of colors to all vertices.
       Please note that there  may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    public boolean _graphColoring(int graph[][], int m)
    {
        // Initialize all color values as 0. This
        // initialization is needed correct
        // functioning of isSafe()
        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;
 
        // Call graphColoringUtil() for vertex 0
        if (
            !graphColoringUtil(
                graph, m, color, 0))
        {
            // System.out.println(
            //     "Solution does not exist");
            return false;
        }
 
        // Print the solution
        printSolution(color);
        return true;
    }
    boolean isSolved = false;
    /* A utility function to print solution */
    void printSolution(int color[])
    {
        isSolved = true;
        // System.out.println(
        //     "Solution Exists: Following"
        //     + " are the assigned colors");
        // for (int i = 0; i < V; i++)
        //     System.out.print(" " + color[i] + " ");
        // System.out.println();
    }

    public boolean graphColoring(boolean graph[][], int m, int n) {
        isSolved = false;
        V = n;
        int g[][] = new int[n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                g[i][j] = graph[i][j] ? 1 : 0;
            }
        }
        
        _graphColoring(g, m);
        
        return isSolved;
    }
}