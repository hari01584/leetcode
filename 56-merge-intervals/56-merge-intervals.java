/*
explanation: the solution works by saving the last element end and comparing it with current element start, if there is no overlaps (ie last end < current start) then it simply adds the element to list. however if there is an overlap then it will pop last element from list and set the end to the current element end.

testcases:
[[1,3],[2,6],[8,10],[15,18]] -> Works

Time & Space Complexity:
O(n) and O(n), Since single linear loop is required over elements therefore it has time complexity of O(n) and since single array is used with linear space therefore it has space complexity of O(n).
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        int l = intervals.length;
        List<int[]> list = new ArrayList<int[]>();

        java.util.Arrays.sort(intervals, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            }
        });

        int ls = -1;
        for(int i=0; i<l; i++){
            int s = intervals[i][0];
            int e = intervals[i][1];
            
            if(s <= ls){
                //Merge mofo
                int[] x = list.remove(list.size()-1);
                x[0] = Math.min(x[0], s);
                x[1] = Math.max(x[1], e);
                
                // System.out.println(x[0]+" "+x[1]+" "+s+" "+e);

                list.add(x);
                
                ls = x[1];
            }
            else{
                list.add(intervals[i]);
                ls = e;
            }
        }
        
        int[][] ret = new int[list.size()][2];
        for(int i=0; i<list.size(); i++){
            ret[i] = list.get(i);
        }
        
        return ret;
    }
}