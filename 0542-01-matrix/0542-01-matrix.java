/*
explanation: 01 matrix using graph bfs algorithm, the logic works by using bfs to first add all 0s to queues, and then
crawl over all elements to find the distance using bfs, additionally it only takes the least of nodes and thus generates
an optimized solution!

testcase: [[0,0,0],[0,1,0],[0,0,0]] -> Works

Time & Space Complexity: O(n^2) & O(n^2): Both time and space complexity is quadratic due to matrix used and each element
traversed.
*/
class Solution {
    class E {
        int i;
        int j;
        int k;
        
        E(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        // Contaiminate!
        int[][] visited = new int[mat.length][mat[0].length];
        for (int[] v : visited) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }
        
        // Do bfs traversal to get each elements!
        LinkedList<E> queue = new LinkedList<E>();
        
        // For first iteration, push all first 0 elements!
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    // this is a generator node (ie from where bfs would be starting later on, so add it up!)
                    queue.add(new E(i, j, -1));
                }
            }
        }
        
        // Now do a bfs reach traversal and get all elements!
        while (queue.size() > 0) {
            E element = queue.poll();

            // If element is out of index, well it sucks :)
            if (element.i < 0 || element.i >= mat.length || element.j < 0 || element.j >= mat[0].length)
                continue;

            // Now this is the element we get, find if this element need crawling (ie if it is 0 or 1)
            if (element.k != -1 && mat[element.i][element.j] == 0) {
                // This is already zero man, why will you even come back? huh??
                continue;
            }

            // If this is visited beforehand with less distance then skip
            if (visited[element.i][element.j] < element.k + 1) {
                continue;
            }

            // First, assign this node which is 1, to cost which is to reach (ie k + 1)
            visited[element.i][element.j] = element.k + 1;

            // Explore all the neighbours now and explore surrounding nodes!
            queue.add(new E(element.i - 1, element.j, element.k + 1));
            queue.add(new E(element.i + 1, element.j, element.k + 1));
            queue.add(new E(element.i, element.j - 1, element.k + 1));
            queue.add(new E(element.i, element.j + 1, element.k + 1));
        }

        // System.out.println(Arrays.deepToString(visited).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        return visited;
    }
}