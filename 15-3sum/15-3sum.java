/*
explanation: program sorts using maintaining a hashmap, two elements are picked in order and the existence of third is checked using hashmap, if found the elements are sorted and added to a set, which maintains uniqueness of elements. finally the set is converted to list and returned as output.

testcase:
[-1,0,1,2,-1,-4] -> Works
*/

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> out = new HashSet<List<Integer>>();
        
        // Arrays.sort(nums);
        
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int v = nums[i];
            if (map.get(v) == null)
                map.put(v, new ArrayList<Integer>(Arrays.asList(i)));
            else
                map.get(v).add(i);
        }
        
//         for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
//             System.out.println(entry.getKey()+" : "+entry.getValue());
//         }
        int s = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            int k = entry.getKey();
            ArrayList<Integer> vk = entry.getValue();
            int rvk = vk.remove(0);
            
            int v = s;
            for (Map.Entry<Integer, ArrayList<Integer>> e2 : map.entrySet()) {
                if(v > 0){
                    v--;
                    continue;
                }
                int i = e2.getKey();
                int j = - (k + i);
                
                ArrayList<Integer> vi = e2.getValue();
                if(vi == null || vi.isEmpty()) continue;
                int rvi = vi.remove(0);

                ArrayList<Integer> vj = map.get(j);
                if(vj == null || vj.isEmpty()){
                    vi.add(rvi);
                    continue;
                }
                
                // System.out.println(i+"<->"+j+"<->"+k);
                int a[] = {i, j, k};
                Arrays.sort(a);
                
                List<Integer> la = new ArrayList<>(Arrays.asList(a[0], a[1], a[2]));
                
                out.add(la);
                
                vi.add(rvi);
            }
            
            vk.add(rvk);
            s++;
        }
        List<List<Integer>> mainList = new ArrayList<List<Integer>>();
        mainList.addAll(out);
        return mainList;
    }
}