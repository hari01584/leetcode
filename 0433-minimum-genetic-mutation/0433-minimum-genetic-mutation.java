/*
explanation: min genetic mutation using bfs, works by maintaining hashmap and graph theory! visualizing it we simply start with
start as our starting node in graph and its child as all the nodes in bank with only one letter difference between start and element, now we add this to our end of queue and keep iterating while marking the node as visited till we get the minimum value.

testcase:
"AACCGGTT"
"AACCGGTA"
["AACCGGTA"] -> Works

Time & Space Complexity: Time and space complexity is n^2 where n is no of character.
*/
class Solution {
    String start;
    String end;
    String[] bank;
    
    public String replace(String str, int index, char replace){     
        if(str==null){
            return str;
        }else if(index<0 || index>=str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[index] = replace;
        return String.valueOf(chars);       
    }

    
    boolean validGene(String s){
        for (String element : bank) {
            if (element.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    int difference(String s1, String s2){
        int diff = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) diff++;
        }
        return diff;
    }
    
    public int minMutation(String start, String end, String[] bank) {
        this.start = start;
        this.end = end;
        this.bank = bank;
        
        HashMap<String, Integer> map = new HashMap<>();
        map.put(start, 0);
        
        Queue<String> que = new LinkedList<>();
        que.offer(start);
        
        int ans = Integer.MAX_VALUE;
        
        while(que.size() > 0){
            String source = que.poll();
            // System.out.println("Source "+source);

            if(difference(source, end) == 0){
                ans = Math.min(ans, map.get(source));
                // System.out.println("Could be a min ans, storing "+source+" as v:"+ans);
                // String found, end reached?
                // return ans;
            }
            
            for(int i=0; i<bank.length; i++){
                String nstr = bank[i];
                if(map.containsKey(nstr)) continue;
                int diff = difference(source, nstr);
                // System.out.println("Match "+nstr+" with diff:"+diff);
                if(diff == 1){
                    // New str path cost = source cost + 1
                    map.put(nstr, map.get(source)+1);
                    que.offer(nstr);
                }
            }
        }
        
        if(ans < 0 || ans > start.length()) return -1;
        
        return ans;
    }
    
//     int minMutate(int index, String s){
//         System.out.println("Index "+index+" with str:"+s);

//         // if(index >= s.length()){
//         //     // Cursor outside string length
//         //     return 0;
//         // }
        
//         if(s.equals(end)) return 0;
                
//         int min = Integer.MAX_VALUE;
//         for(int i=0; i<s.length(); i++){
//             if(s.charAt(i)!=end.charAt(i)){
//                 String newstr = replace(s, i, end.charAt(i));
//                 System.out.println("Found invalid "+s.charAt(i)+", patching up "+s+" -> "+newstr);
//                 if(!validGene(newstr)){
//                     // Invalid gene, dont take into account
//                     System.out.println("Invalid gene");
//                     continue;
//                 }
//                 int mm = minMutate(0, newstr);
//                 System.out.println("Mutating "+newstr+", min="+mm);
//                 min = 1 + Math.min(min, mm);
//             }
//         }
        
//         return min;
//     }
    
//     int difference(String s1, String s2){
        
//     }
    
//     int minMutateGraph(String s){
        
//     }

}