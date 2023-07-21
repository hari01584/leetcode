/*
explanation: spiral matrix inspired by striver's clean spiral code! Algorithm works by traversing each of four sections of
matrix and adding any elements to list!

testcase: [[1,2,3],[4,5,6],[7,8,9]] -> Works

Time & Space Complexity: O(mn) & O(mn): Both time and space complexity is quadratic or mn due to iteration over each element required and the storage for adding elements to arraylist.
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> arr = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Top section
            for (int k=left; k<=right; k++) {
                arr.add(matrix[top][k]);
            }
            top++;

            // Go down right
            for (int k=top; k<=bottom; k++) {
                arr.add(matrix[k][right]);
            }
            right--;

            if (top <= bottom) {
                // Right to left bottom
                for (int k=right; k>=left; k--) {
                    arr.add(matrix[bottom][k]);
                }
                bottom--;
            }

            if (left<=right) {
                // Bottom to up
                for (int k=bottom; k>=top; k--) {
                    arr.add(matrix[k][left]);
                }
                left++;
            }
        }
        
        return arr;
    }
}