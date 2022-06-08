/*
explanation: Reverse linkedlist by k index using simple loops, the idea here is to use similar loops like reversing normal linked list but instead saving head of previous sections of linkedlist, when reached appropriately it will connect to the last element in next section.

testcase:
[1,2,3,4,5]
2
-> Works
Initially localStart is 1 and loop iterates over to 1..2..3..., when it reaches 2, it sets the previous tlocalStart to it, ie -1 -> Next = 2, now in next when it reaches 3, it sets localStart that and when it reaches 4, it uses the previous to previous localStart (ie 1) and sets it to 4. After all this the remaining portion is set using the next pointer logic.

Time and Space Complexity: O(n) and O(1): Since only one loop is required, therefore time complexity is O(n) and since constant amount of variables are there in program (without recursions) thf. space complexity is O(1)
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
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int l = 0;
        ListNode s = head;
        while(s!=null){
            l++;
            s=s.next;
        }
        if(k == 1){
            return head;
        }
        if(l == k){
            // Simple reverse
            ListNode prev = null;
            ListNode current = head;
            ListNode next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
            return head;
        }
        
        
        ListNode localStart = new ListNode(-1);;
        ListNode tlocalStart = new ListNode(-1);
        
        ListNode localEnd = new ListNode(-1);
        ListNode prev = head;
        
        ListNode hnew = null;
        
        ListNode temp = head;
        int i = 0;
        while(temp != null && i < l - l%k){
            if(i%k == 0){
                System.out.println("Setting localstart to "+temp.val);
                localStart = temp;
                temp = temp.next;
                i++;
                prev = localStart;
                continue;
            }
            else if(i%k == k - 1){
                localEnd = temp;
                System.out.println("tail "+tlocalStart.val);
                System.out.println("to "+temp.val);
                System.out.println("new "+localStart.val);
                if(hnew == null) hnew = temp;
                tlocalStart.next = temp;
                tlocalStart = localStart;
            }
            
            ListNode t = temp;
            temp = temp.next;
            t.next = prev;
            
            prev = t;
            
            i++;
        }
        
        System.out.println("Debag");
        
        if(temp!=null){
            localStart.next = temp;
        }
        else{
            localStart.next = null;
        }
//         System.out.println(temp.val);
        
//         System.out.println("Debag End");


        // Correction for extra remaining length
        // if(i%k!=0){
        //     tlocalStart.next = localStart;
        //     prev.next = null;
        // }
        // else{
        //     // localStart.next = null;
        // }


        ListNode t = head;
        for(int z=0; z<10; z++){
            if(t == null) break;
            System.out.println(t.val);
            t = t.next;
        }
        
        return hnew;
    }
}