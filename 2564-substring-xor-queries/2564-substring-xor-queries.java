/*
explanation: substring xor using string match algorithm! This program exploits the facts that for any three numbers a, b, c.. if a^b = c then a will be equal to b^c! Therefore we xor the first and second of query q to find the decimal number we need to look into string s, then converting this to string and finding index will give our first element!

testcase: "101101"
[[0,5],[1,2]] -> Works

Time & Space Complexity: O(n^2) & O(n): Time and space complexity of this program is quadratic and linear respectivey! Since we iterate and try finding substring in string each time therefore time complexity is quadratic, also space complexity is linear due to storage space required!
*/
class Solution {
    class E {
        int e;
        int qdx;
        
        E(int e, int qdx){
            this.e = e;
            this.qdx = qdx;
        }
    }
    
    public int[][] substringXorQueries(String s, int[][] queries) {
        int[][] ans = new int[queries.length][2];
        for(int[] a : ans) Arrays.fill(a, -1);
        
        HashMap<Integer, List<E>> _contain_cache = new HashMap<>();

        int z=0;
        int indexOf0 = s.indexOf("0");
        for(int[] q:queries){
            int st = q[0];
            int ed = q[1];
            int dcm = st ^ ed;
            
            if(dcm == 0){
                // Special case :)
                ans[z++] = new int[]{indexOf0, indexOf0};
                continue;
            }
            
            List<E> arr = _contain_cache.getOrDefault(dcm, new ArrayList<E>());
            arr.add(new E(dcm, z++));
            _contain_cache.put(dcm, arr);
        }
        
        for(int i=0; i<s.length(); i++){
            StringBuilder str = new StringBuilder();
            if (s.charAt(i) == '0') continue;
            for(int j=i; j<s.length(); j++){
                // st += s.charAt(j);
                str.append(s.charAt(j));
                
                if (str.length() > Integer.toBinaryString(Integer.MAX_VALUE).length()) break;

                int parse = Integer.parseInt(str.toString(), 2);
                List<E> arr = _contain_cache.getOrDefault(parse, new ArrayList<E>());
                for(E e : arr){
                    ans[e.qdx] = new int[]{i, j};
                }
                _contain_cache.remove(parse);
            }
        }
        
        
        
        return ans;
    }
}