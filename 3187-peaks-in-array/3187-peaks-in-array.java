class Seg<T> {
    public interface SegInterface<T> {
        T combine(int l, int r, int m, T left, T right);
        T build_default(int ri);
        String log_string(T node);
    }

    T[] seg;
    T defaultNodeValue;
    SegInterface<T> builder;
    int n;

    Seg(int n, T[] _seg, SegInterface<T> b) {
        // this.seg = new T[4*n];
        this.n = n;
        seg = _seg;
        builder = b;
    }

    T _build(int i, int l, int r) {
        if (l >= r) {
            seg[i] = builder.build_default(l);
            return seg[i];
        }

        int m = (l + r)/2;
        // Else build left and right subtree first!
        T lt = _build(2*i, l, m);
        T rt = _build(2*i+1, m+1, r);

        seg[i] = builder.combine(l, r, m, lt, rt);
        return seg[i];
    }

    void build() {
        _build(1, 0, n-1);
    }
    
    void _update(int i, int l, int r, int arr_index, T node_val) {
        // System.out.println("We reach "+i+" "+l+" "+r+" "+" arrindex: "+arr_index);
        if (l == r && l == arr_index) {
            // At this point, update our value!
            seg[i] = node_val;
            return;
        }
        if (arr_index < l || arr_index > r) {
            // Out side
            return;
        }
        
        // Else we first do search!
        int m = (l+r)/2;
        _update(2*i, l, m, arr_index, node_val);
        _update(2*i+1, m+1, r, arr_index, node_val);
        
        // Recalculate this node value again
        seg[i] = builder.combine(l, r, m, seg[2*i], seg[2*i+1]);
    }
    
    public void update(int arr_index, T node) {
        // Update!
        _update(1, 0, n-1, arr_index, node);
    }
    
    T _get(int i, int l, int r, int arr_left, int arr_right) {
        // System.out.println("Try "+i+" "+l+" "+r+" "+arr_left+" "+arr_right);

        // First is full overlap
        if (l == arr_left && r == arr_right) {
            return seg[i]; // Simply return whatever is there :)
        }
        
        // If fully covered then return
        // if (arr_left < l && r > arr_right) {
        //     return seg[i];
        // }
        
        // Then is NO overlap...
        if (r < arr_left || l > arr_right) {
            return null;
        }
        
        // If One element only, return that
        // if (l == r) {
        //     if (l == arr_left)
        //         return seg[i];
        //     else
        //         return null;
        // }

        int wanted_left = Math.max(arr_left, l);
        int wanted_right = Math.min(arr_right, r);
        
        // This means we have merge overlaps, therefore get left and right component and merge!
        int m = (l + r) / 2;
        T lt = _get(2*i, l, m, Math.max(arr_left, l), Math.min(arr_right, m));
        T rt = _get(2*i+1, m+1, r, Math.max(m+1, arr_left), Math.min(r, arr_right));

        // System.out.println("Debug "+i+" "+l+" "+r+" "+ "lt: "+lt+" , rt: "+rt);

        // If any is null, then return other!
        if (lt == null) {
            return rt;
        }
        if (rt == null) {
            return lt;
        }
        
        return builder.combine(Math.max(l, arr_left), Math.min(r, arr_right), m, lt, rt);
    }
    
    public T get(int arr_left, int arr_right) {
        return _get(1, 0, n-1, arr_left, arr_right);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Print backing buffer!
        sb.append("DUMPING SEGMENT TREE\n");
        for (int i=0; i<seg.length; i++) {
            String seg_str = "null";
            if (seg[i] != null) {
                seg_str = seg[i].toString();
            }
            sb.append(String.format("%d %s\n", i, seg_str));
        }
        sb.append("END DUMP\n");
        return sb.toString();
    }
}

class Solution {
    class SegNode {
        int peaks;
        int l_val;
        int r_val;
        
        SegNode() {
            peaks = 0;
        }
        
        SegNode(int p, int l, int r) {
            peaks = p;
            l_val = l;
            r_val = r;
        }
        
        SegNode(SegNode other) {
            this.peaks = other.peaks;
            this.l_val = other.l_val;
            this.r_val = other.r_val;
        }
        
        @Override
        public String toString() {
            return String.format("%d - %d, %d", peaks, l_val, r_val);
        }
    }
    
    public class SegNodeInterface implements Seg.SegInterface<SegNode> {
        int[] nums;

        public SegNodeInterface(int[] nums) {
            this.nums = nums;
        }
    
        public SegNode build_default(int pos) {
            return new SegNode(0, nums[pos], nums[pos]);
        }
        
        public SegNode combine(int l, int r, int m, SegNode lt, SegNode rt) {
            int total_peaks = lt.peaks + rt.peaks;
            // check if intersection can form more peaks?
            if (r-l >= 2) {
                if (m-1 >= l && nums[m-1] < nums[m] && nums[m] > nums[m+1] && m+1 <= r) {
                    total_peaks++;
                } else if (m+2 <= r && m >= l && nums[m] < nums[m+1] && nums[m+1] > nums[m+2]) {
                    total_peaks++;
                }
            }
            // System.out.println("Trying to combine at m="+m+" lt: "+lt+" rt: "+rt+" ans: "+total_peaks);

            return new SegNode(total_peaks, nums[l], nums[r]);
        }
        
        public String log_string(SegNode node) {
            return node.toString();
        }
    }

    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        SegNode[] buffer = new SegNode[4*nums.length];
        
        SegNodeInterface inf = new SegNodeInterface(nums);
        Seg<SegNode> segment = new Seg(nums.length, buffer, inf);

        // First time build segtree
        segment.build();
        
        List<Integer> answer = new ArrayList<Integer>();
        // System.out.println(segment);
        // System.out.println(segment);

        // System.out.println("NOW IT WILL MATTER");

        for (int[] q : queries) {
            if (q[0] == 1) {
                // Query!
                answer.add(segment.get(q[1], q[2]).peaks);                
            } else {
                nums[q[1]] = q[2];
                segment.update(q[1], new SegNode(0, q[2], q[2]));
            }
        }
        
        // System.out.println("Get val");
        // segment.update(5, new SegNode(1, 2, 2));
        // System.out.println(segment.get(0, 4));

        return answer;
    }
}