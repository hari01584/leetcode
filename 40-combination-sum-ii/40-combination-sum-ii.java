/*
explanation: backtracking I with additional

*/

class Solution {
    List<Integer> currentList = new ArrayList();
    List<Integer> doneList = new ArrayList();

    List<List<Integer>> root = new ArrayList();
    
    public void addIfNot(List<Integer> item, List<List<Integer>> root){
        // Collections.sort(item);  
        for (List<Integer> e : root) {
            if (e.equals(item) == true) {
                return;
            }
        }
        root.add(item);
    }

    public void backsum(int[] candidates, int sum, int target, int index){
        if(sum == target){
            // Adds to list
            // System.out.println("Adding to list");
            // for (Integer i : currentList){
            //     System.out.println(i+" ");
            // }
            addIfNot(new ArrayList(currentList), root);
            // currentList.clear();
            return;
        }
        else if(sum > target) return;
        
        
        for(int i=index; i<candidates.length; i++){
            if(i > index && candidates[i] == candidates[i-1]) continue;
            // if(doneList.contains(candidates[i])) continue;
            // if(currentList.get(currentList.size()-1) == candidates[i]) continue;
            sum += candidates[i];
            currentList.add(candidates[i]);
            // System.out.println(candidates[i]+" "+sum);
            backsum(candidates, sum, target, i+1);
            // index++;
            currentList.remove(currentList.size() - 1);
            sum -= candidates[i];
            
            // if(sum == 0){
            //     // System.out.println("Added " + candidates[i]+" to ban list with sum "+sum);
            //     doneList.add(candidates[i]);
            // }
        }
        
        // System.out.println("L " + candidates[index]+" "+sum);
        // doneList.add(candidates[index]);
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        backsum(candidates, 0, target, 0);
        
        return root;
    }
}