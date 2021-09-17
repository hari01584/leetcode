struct Node{
    int key;
    Node* next;
    
    Node(int k, Node* n):key(k),next(n){}
};

class MyHashSet {
public:
    const static int H_SIZE = 1024;
    Node* map[H_SIZE];

    /** Initialize your data structure here. */
    MyHashSet() {
        for(int i=0; i<H_SIZE; i++){
            map[i] = NULL;
        }
    }
    
    void add(int key) {
        int hsh = hash(key);
        Node* h = map[hsh];
        Node* n = new Node(key, NULL);
        if(h==NULL){
            map[hsh] = n;
        }
        else{
            while(h->next != NULL){
                if(h->key == key){
                    return;
                }
                h = h->next;
            }
            if(h->key == key){
                return;
            }
            h->next = n;
        }
    }
    
    void remove(int key) {
        int hsh = hash(key);
        Node* h = map[hsh];
        Node* prev = NULL;
        while(h!=NULL){
            if(h->key == key){
                if(prev!=NULL){
                    prev->next = h->next;
                }
                else{
                    map[hsh] = h->next;
                }
                Node* t = h;
                h = h->next;
                delete t;
                return;
            }
            prev = h;
            h = h->next;
        }
    }
    
    /** Returns true if this set contains the specified element */
    bool contains(int key) {
        int hsh = hash(key);
        Node* h = map[hsh];
        while(h!=NULL){
            if(h->key == key){
                return true;
            }
            h=h->next;
        }
        return false;
    }
    
    int hash(int index){ //Hashing function
        return index % (H_SIZE);
    }
};

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet* obj = new MyHashSet();
 * obj->add(key);
 * obj->remove(key);
 * bool param_3 = obj->contains(key);
 */