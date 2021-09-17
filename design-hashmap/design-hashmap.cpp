//HSMP
struct Node{
    int key;
    int value;
    Node* next;
    
    Node(int k, int v, Node* n):key(k),value(v),next(n){}
};
class MyHashMap {
public:
    const static int H_SIZE = 1024;
    Node* map[H_SIZE];
    
    MyHashMap() {
        for(int i=0; i<H_SIZE; i++){
            map[i] = NULL;
        }
    }
    
    /** value will always be non-negative. */
    void put(int key, int value) {
        int hsh = hash(key);
        Node* h = map[hsh];
        Node* n = new Node(key, value, NULL);
        if(h==NULL){
            map[hsh] = n;
        }
        else{
            while(h->next != NULL){
                if(h->key == key){
                    h->value = value;
                    return;
                }
                h = h->next;
            }
            if(h->key == key){
                h->value = value;
                return;
            }
            h->next = n;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    int get(int key) {
        int hsh = hash(key);
        Node* h = map[hsh];
        while(h!=NULL){
            if(h->key == key){
                return h->value;
            }
            h=h->next;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
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
    
    int hash(int index){ //Hashing function
        return index % (H_SIZE);
    }
};

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap* obj = new MyHashMap();
 * obj->put(key,value);
 * int param_2 = obj->get(key);
 * obj->remove(key);
 */