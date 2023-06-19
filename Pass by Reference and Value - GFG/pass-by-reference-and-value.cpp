//{ Driver Code Starts
// Initial Template for C++

#include <bits/stdc++.h>
using namespace std;


// } Driver Code Ends
// User function Template for C++

/*
explanation: passed by value and reference in c.

testcase: works

time & space complexity: O(1) & O(1): Linear because of countable instructions and no extra space required.
*/
class Solution {
  public:
    vector<int> passedBy(int a, int &b) {
        std::vector<int> v = { a+1, b+2 };
        return v;
    }
};

//{ Driver Code Starts.

int main() {
    int t;
    cin >> t;
    while (t--) {
        int a, b;
        cin >> a >> b;
        Solution obj;
        vector<int> ans = obj.passedBy(a, b);
        cout << ans[0] << " " << ans[1] << "\n";
    }
    return 0;
}

// } Driver Code Ends