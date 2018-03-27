//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Source code: https://www.sanfoundry.com/java-program-implement-min-heap/ //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////

package runs;

class MinHeap {
    private String[] Heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new String[this.maxsize + 1];
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return false;
        }
        return true;
    }

    public void swap(int fpos, int spos) {
        String tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void minHeapify(int pos) {
        String parent = Heap[pos];
        String left = Heap[leftChild(pos)];
        String right = Heap[rightChild(pos)];

        if (left == null)
            return;

        if (right != null) {
            if (parent.compareTo(left) > 0 || parent.compareTo(right) > 0) {
                if (left.compareTo(right) < 0) {
                    swap(pos, leftChild(pos));
                    return;

                } else {
                    swap(pos, rightChild(pos));
                    return;

                }
            }
        }
        if (parent.compareTo(left) > 0) {
            swap(pos, leftChild(pos));

        }
    }

    public void insert(String element) {
        size++;
        if (size <= maxsize)
            Heap[size] = element;
        else
            System.err.println("Error: '" + element + "' - heap at capacity");

    }

    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i]
                    + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }

    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public String remove() {
        String popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }


    public String replace(String newRoot) {
        String popped = Heap[FRONT];
        Heap[FRONT] = newRoot;
        if (size > 1)
            downHeapify(FRONT);
        return popped;
    }

    private void downHeapify(int pos) {

        String parent = Heap[pos];
        String left = Heap[leftChild(pos)];
        String right = Heap[rightChild(pos)];

        while (right == null || parent.compareTo(left) > 0 || parent.compareTo(right) > 0) {
            minHeapify(pos);
            pos++;
            if (right == null)
                return;
            parent = Heap[pos];
            left = Heap[leftChild(pos)];
            if (left == null)
                return;
            right = Heap[rightChild(pos)];
        }
    }

    public void setSize(int size) { this.size = size; }

    public int getSize() { return size; }

    public void setLastItem(String item) { Heap[size] = item; }

    public String getLastItem() { return Heap[size]; }

    public String minValue() { return Heap[1]; }

    public String root() { return Heap[FRONT]; }


}

