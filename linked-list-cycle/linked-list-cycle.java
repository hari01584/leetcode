/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
Explanation: Hare and tortoise algorithm or Floyd's cycle detection, one pointer is traversing twice as fast as second pointer, If there is no cycle then both pointer should reach end and hence become null, however if there is cycle then the two pointers which should never ideally meet each other (due to different speed and same starting point) will meet and become equal, Hence detecting the cycle!

Testcase:
[3,2,0,-4]
1
hare goes 3->2->0->-4->3
tor. goes 3->0->3-> 0->3

Here the
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null) return false;
        ListNode hare = head.next, tortoise = head;
        
        while(hare!=null && hare.next!=null && hare.next.next!=null && tortoise!=null && tortoise.next != null){
            if(hare==tortoise){
                return true;
            }
            
            tortoise=tortoise.next;
            hare=hare.next.next;
        }
        return false;
    }
}