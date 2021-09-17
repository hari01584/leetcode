/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
/**
Explanation: Program loops over the original linkedlist and add non duplicate elements to another linkedlist, Since the arrays are sorted then only checking if the last element added is same to the iterative element is enough! In case element is unique then the element is added and last element record (var n) is set to new value;

Testcase 1, 1
Works, while looping over
*/
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        // Exceptional case if empty head is passed
        if(head==NULL) return head;
        
        ListNode* n = head;
        // save first location and initilized new list with first element of head, because there is no chances of duplicate element right now (As the newlist is empty)
        ListNode* top = n;
        int p = n->val; // Maintain value of last added node in new LinkedList.
        while((head = head->next) != NULL){
            if(head->val != p){
                p = head->val;
                n->next = head;
                n = n->next;
            }
        }
        
        n->next = NULL;
        return top;
    }
};