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
        String left = null;
        String right = null;
        if (leftChild(pos) <= maxsize)
            left = Heap[leftChild(pos)];
        if (rightChild(pos) <= maxsize)
            right = Heap[rightChild(pos)];

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

    public void insert(String element, String fileName) {
        size++;
        if (size <= maxsize)
            Heap[size] = element + " " + fileName;
        else
            System.err.println("Error: '" + element + "' - heap at capacity");

    }

    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            if ((2 * i + 1) <= this.maxsize) {
                System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i]
                        + " RIGHT CHILD :" + Heap[2 * i + 1]);
                System.out.println();
            }
            else if ((2 * i) <= this.maxsize) {
                System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i]
                        + " RIGHT CHILD : *null");
                System.out.println();
            }
            else {
                System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : *null"
                        + " RIGHT CHILD : *null");
                System.out.println();
            }
        }
    }

    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public String remove() {
        String popped = Heap[FRONT];
        Heap[FRONT] = Heap[size];
        setItem(size, null);
        minHeapify(FRONT);
        size--;
        return popped;
    }


    public String replace(String newRoot) {
        String popped = Heap[FRONT];
        Heap[FRONT] = newRoot;
        if (size > 1)
            downHeapify(FRONT);
        return popped;
    }
    
    public String removeFront(){
	String popped = Heap[FRONT];
	return popped;
    }
    
    public void replaceFront(String newRoot, String fileName){
	String one = newRoot + " " + fileName;
	Heap[FRONT] = one;
        if (size > 1){
	    System.err.println("IN if");
            downHeapify(FRONT);
	}
    }

    public void downHeapify(int pos) {

        String parent = Heap[pos];
        String left = Heap[leftChild(pos)];
        String right = Heap[rightChild(pos)];

        while (right == null || parent.compareTo(left) > 0 || parent.compareTo(right) > 0) {
            minHeapify(pos);
            pos++;
            if (pos > maxsize)
                    return;
            if (right == null)
                return;
            parent = Heap[pos];
            if (leftChild(pos) <= maxsize)
                left = Heap[leftChild(pos)];
            if (left == null)
                return;
            if (rightChild(pos) <= maxsize)
                right = Heap[rightChild(pos)];
        }
    }

    public void setSize(int size) { this.size = size; }

    public int getSize() { return size; }

    public void setItem(int pos, String item) { Heap[size] = item; }

    public String getItem(int pos) { return Heap[pos]; }

    public String minValue() { return Heap[1]; }

    public String root() { return Heap[FRONT]; }


}

