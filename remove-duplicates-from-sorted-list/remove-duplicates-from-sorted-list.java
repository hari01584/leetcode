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
/*
Inspiration: https://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
Compares each element of list to the next elements, if same then delete the next elements and try the condition again.. do until the next element is unique and then go to next element. It kinds of compacts the farther elements into its earlier self.
*/
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode top = head;
        while(head!=null && head.next!=null){
            if(head.next.val == head.val){
                head.next = head.next.next;
            }
            else head=head.next;
        }
        return top;
    }
}