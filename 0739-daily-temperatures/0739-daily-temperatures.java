/*
explanation: daily temp using monotonic decreasing stack, since problem wants us to find next greater temp therefore this can be modelled to monptonic stack problem!

testcase: [73,74,75,71,69,72,76,73] -> Works

Time & Space complexity: time complexity is linear also the space complexity is also linear! since linear loop for stack is used, also the space required to maintain it is also linear!
*/
class Solution {
    
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        
        Stack<Pair<Integer, Integer>> monotone = new Stack<>();
        for(int i=0; i<temperatures.length; i++){
            int e = temperatures[i];
            
            while(monotone.size() > 0 && monotone.peek().getKey() < e){
                Pair<Integer, Integer> p = monotone.pop();
                res[p.getValue()] = i - p.getValue();
            }
            
            monotone.push(new Pair(e, i));

            // System.out.println(Arrays.toString(monotone.toArray()));
        }
        
        return res;
    }
}