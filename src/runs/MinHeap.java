//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Source code: https://www.sanfoundry.com/java-program-implement-min-heap/ //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////

package runs;

class MinHeap
{
    private String[] Heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    public MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new String[this.maxsize + 1];
    }

    private int parent(int pos)
    {
        return pos / 2;
    }

    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos)
    {
        String tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void minHeapify(int pos)
    {
        if (!isLeaf(pos))
        {
            if ( Heap[pos].compareTo(Heap[leftChild(pos)]) == 1  || Heap[pos].compareTo(Heap[rightChild(pos)]) == 1)
            {
                if (Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) == -1)
                {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(String element)
    {
        Heap[++size] = element;
        int current = size;

        if (size == 1) {
            return;
        }

        while (Heap[current].compareTo(Heap[parent(current)]) == -1) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i]
                    + " RIGHT CHILD :" + Heap[2 * i  + 1]);
            System.out.println();
        }
    }

    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }

    public String remove()
    {
        String popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }
}