/*
Explanation: Permutation of integer list using recursive function, this works on the principle of diving the permutation into smaller parts and solving them on your own, Recursion is applied as follows:

for base case, one element nested list is checked for, and if found is returned, ie [[6]], [[9]], .... etc are returned but [[6, 1]], [[6], [7]] etc are not returned as a base case but processed further.

Recursion algo: example [[1, 2]]
The algorithm loops over each element of the sublists (here only 1) and picks each element, It removes it from the sublist and feed the remaining part to the recursive function, ie in [[1, 2]] element 1 is removed and the list [[2]] is fed again to recursive fxn, Since [[2]] forms a base case therefore it is returned, now the returned nested list is merged with the initial removed element (in this case 1), therefore 1 + [[2]] becomes [[1, 2]], this is then added to the returning list, now for the next element 2 same loop is ran and this time recursive fxn takes [[1]] as input (which is base case), and therefore returns [[1]] as well, this is then added with 2, thf. 2 + [[1]] = [[2, 1]], this is again added to the returning list, when all elements like these are over, the loop ends and the nested list containing all the permutated values are returned. here [[1, 2], [2, 1]]

Testcase:
[1] -> Works, as [[1]] is base case
[1, 2] -> [[1, 2]] = [1+[2], 2+[1]] = [[1, 2], [2, 1]] -> works
[3, 1, 2] -> [3 + [1, 2], 1 + [3, 2], 2 + [3, 1]] = ....... = works
*/

class Solution {
    public List<List<Integer>> toNestList(List<Integer> base){
        List<List<Integer>> d = new ArrayList<>();
        d.add(base);
        return d;
    }
    
    public List<List<Integer>> permut(List<List<Integer>> base){
        if(base.size() <= 1){
            List<Integer> n = base.get(0);
            if(n.size() <= 1) { return base; }
        }
        
        List<List<Integer>> d = new ArrayList<>();
        for(List<Integer> l: base){
            for(int i=0; i<l.size(); i++){
                int v = l.remove(i);
                List<List<Integer>> r = new ArrayList<>(permut(toNestList(l)));
                for(List<Integer> m: r){
                    List<Integer> dummy = new ArrayList<Integer>(m);
                    dummy.add(0, v);
                    d.add(dummy);
                }
                l.add(i, v);
            }
        }
        
        return d;
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int value : nums) {
            list.add(Integer.valueOf(value));
        }
        List<List<Integer>> rt = permut(toNestList(list));
        
        return rt;
    }
}