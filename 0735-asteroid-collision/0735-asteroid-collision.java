/*
explanation: asertoid collison by taking into account all cases! We assume that normal asteriod travels from left to right
thus whenever an asteriod opposite to flow is observed, the algorithm tries to destroy all in path and find correct position.

testcase: [5,10,-5] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic due to two nested loops required, space complexity
is linear due to extra list created for storing states of asteriods.
*/
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int asteriods_index = 0;
        ArrayList<Integer> asteriodsList = new ArrayList<>();

        // 4 Cases
        // Starting asteriods going left (negative signs), ignore because they won't collide anywhere
        while (asteriods_index < asteroids.length && asteroids[asteriods_index] < 0)
            asteriodsList.add(asteroids[asteriods_index++]);

        // Now comes asteriods which can collide, ie going right side.. this will only collide if there
        // is any negative asteriod in left and depending upon magnitude might break up!

        // Loop over each asteriods and check their effects, ie if they collide, destroy or bypass!
        for (; asteriods_index<asteroids.length; asteriods_index++) {
            int ast = asteroids[asteriods_index];
            // Case 2: Asteriod is positive, ie push to arraylist, normally!
            if (ast >= 0) {
                asteriodsList.add(ast);
            }
            
            // Case 3: Asteriod is negative, now you have to see if there is any positive asteriods present
            // before, if there are then one of the two (or both), will surely get destroyed.. ie!
            if (ast < 0) {
                ast = -ast; // Positive this for further calculations

                boolean toAdd = true;
                while (asteriodsList.size() > 0) {
                    int lastElement = asteriodsList.get(asteriodsList.size() - 1);
                    if (lastElement < 0) {
                        // Reached the left moving asteriods now, nothing to destroy this
                        break;
                    }

                    // Case 3.1, bigger asteriods collide smaller one, then destroy mine and break!
                    if (lastElement > ast) {
                        toAdd = false;
                        break;
                    }
                    // Case 3.2: If lastElement == ast (both destroyed)
                    if (lastElement == ast) {
                        toAdd = false; // Destroy negative asteroid too
                        asteriodsList.remove(asteriodsList.size() - 1);
                        break;
                    }
                    // Case 3.3, if lastElement < ast, destroy bigger asteriod and see next element!
                    if (lastElement < ast) {
                        asteriodsList.remove(asteriodsList.size() - 1);
                    }
                }

                // Add this asteriod finally!
                if (toAdd) {
                    asteriodsList.add(-ast);
                }
            }
        }
        
        int[] ans = new int[asteriodsList.size()];
        for (int i=0; i<asteriodsList.size(); i++) {
            ans[i] = asteriodsList.get(i);
        }
        
        return ans;
    }
}