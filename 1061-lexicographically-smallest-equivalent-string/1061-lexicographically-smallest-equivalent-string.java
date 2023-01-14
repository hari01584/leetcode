/*
explanation: smallest equivalent string using ranked dsu, the program works by using the grouping property of dsu to successively find the least character value and group it together with other elements making the least character the head position! Finally we iterate over the baseStr and for each character find its parent (or its minimum grouped lexiographic neighbour), once done we build this string and return the answer!

testcase:
"parker"
"morris"
"parser" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear since iterative order 1 degree loops are used, also space complexityi ls linear due to the storage required to store new string formed.
*/
class Solution {
    class RDSU{
        int n;
        int[] arr;
        RDSU(int n){
            this.n = n;
            arr = new int[n];
            
            for(int i=0; i<n; i++){
                arr[i] = i;
            }
        }
        
        void union(int x, int y){
            int h1 = find(x);
            int h2 = find(y);
            
            if(h1 == h2) return;

            // Find lower!
            int a = Math.min(h1, h2);
            int b = Math.max(h1, h2);
            arr[b] = a;
        }
        
        int find(int x){
            while(arr[x] != x){
                x = arr[x];
            }
            return x;
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        RDSU rdsu = new RDSU(26);
        for(int i=0; i<s1.length(); i++){
            rdsu.union(s1.charAt(i)-'a', s2.charAt(i)-'a');
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<baseStr.length(); i++){
            ans.append((char)(rdsu.find(baseStr.charAt(i)-'a')+'a'));
        }
        
        return ans.toString();
    }
}