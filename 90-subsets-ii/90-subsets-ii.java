/*
explanation: subset I with modification of checking arraylist for already containing value.

*/

class Solution {
    public List<String> generateBitmask(int l){
        List<String> root = new ArrayList<>();
        
        for(int i=0; i<(int)Math.pow(2, l); i++){
            String bs = Integer.toBinaryString(i);
            String pad = "";
            for(int m=0; m<l-bs.length(); m++){
                pad = pad + "0";
            }
            bs = pad + bs;
            root.add(bs);
        }
        
        return root;
    }
    
    public List<Integer> mutate(int[] nums, String mask){
        List<Integer> root = new ArrayList<>();
        for(int i=0; i<mask.length(); i++){
            if(mask.charAt(i) == '1'){
                root.add(nums[i]);
            }
        }
        return root;
    }

    public void addIfNot(List<Integer> item, List<List<Integer>> root){
        Collections.sort(item);  

        for (List<Integer> e : root) {
            if (e.equals(item) == true) {
                return;
            }
        }

        root.add(item);
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> root = new ArrayList<>();
        
        List<String> mask = generateBitmask(nums.length);
        for(int i=0; i<mask.size(); i++){
            // System.out.println(mask.get(i));
            addIfNot(mutate(nums, mask.get(i)), root);
        }
        
        return root;

    }
}