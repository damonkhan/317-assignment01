//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Authors: Damon Khan & Riley Cochrane                                     //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


package runs;

class MakeRuns {

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: <size> <textFile>");
            return;
        }

        //initialize heap

        // Create heap of size k
        int maxSize = Integer.parseInt(args[0]) + 1;
        String file  = args[1];

        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(maxSize);
        minHeap.insert("c");
        minHeap.insert("b");
        minHeap.insert("d");
        minHeap.insert("a");
        minHeap.insert("e");
        minHeap.insert("f");
        Heapify(minHeap);
        minHeap.print();

        System.out.println("Next out is " + minHeap.minValue());
        minHeap.remove();
        minHeap.minHeap();
        minHeap.print();
        System.out.println("Next out is " + minHeap.minValue());


    }

    public static void Heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }

    }
}
