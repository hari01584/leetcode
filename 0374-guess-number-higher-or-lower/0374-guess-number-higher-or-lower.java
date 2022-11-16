/*
explanation: guess game by binary search, simply uses bsearch to find approrpriate middle value!

testcase: 10, 6 -> Works

Time & Space Complexity: time complexity of this program is log(n) since binary search is used, also space complexity is constant because of fixed number of variables!
*/

/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        
        while(low < high){
            int mid = low+(high-low)/2;
            int gs = guess(mid);
            if(gs == 0) return mid;
            else if(gs == -1) high = mid-1;
            else low = mid+1;
        }
        
        return low;
    }
}