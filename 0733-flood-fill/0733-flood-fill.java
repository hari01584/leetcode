/*
explanation: simple recursive flood fill algorithm using recursion! It works by first assigning starting pixel and then recursively checking for bounds and value of neighbour pixels, if pixels are good then the algorithm will set it to color and check more boundaries.

testcase:
[[1,1,1],[1,1,0],[1,0,1]]
1
1
2 -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear, due to recursion and the space stack maintained for it. 

*/
class Solution {
    int startingPixel = -1;
    int mark[][];
    
    public int getVal(int[][] arr, int x, int y){
        if(x<0 || x>=arr.length || y<0 || y>=arr[0].length) return -1;
        else return arr[x][y];
    }
    
    public void fill(int[][] img, int x, int y, int color){
        if(img[x][y] != startingPixel) return;
        if(mark[x][y] != 0) return;
        
        img[x][y] = color;
        mark[x][y] = 1;
        
        if(getVal(img, x+1, y) != -1){
            fill(img, x+1, y, color);
        }
        
        if(getVal(img, x-1, y) != -1){
            fill(img, x-1, y, color);
        }
        
        if(getVal(img, x, y+1) != -1){
            fill(img, x, y+1, color);
        }
        
        if(getVal(img, x, y-1) != -1){
            fill(img, x, y-1, color);
        }
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        mark = new int[image.length][image[0].length];
        startingPixel = image[sr][sc];
        fill(image, sr, sc, color);
        return image;
    }
}