/*
explanation: counts total length of linkedlist initially and stores it in a variable, at every call to get random will generate a number between 0, length and hence output val after skipping pointer to this random generated number!

testcase:
["Solution","getRandom","getRandom","getRandom","getRandom","getRandom"]
[[[1]],[],[],[],[],[]] -> Works
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.concurrent.ThreadLocalRandom;

class Solution {
    int max = 0;
    ListNode head;
    public Solution(ListNode head) {
        this.head = head;
        
        while(head != null){
            max++;
            head=head.next;
        }
    }
    
    public int getRandom() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, max + 1);
        ListNode tmp = head;
        for(;randomNum>1;randomNum--) tmp = tmp.next;
        return tmp.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */