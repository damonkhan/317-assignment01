//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Authors: Damon Khan & Riley Cochrane                                     //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


package runs;

class MakeRuns {

    public static void main(String args[]) {
//        if (args.length != 2) {
//            System.out.println("Usage: <size> <textFile>");
//            return;
//        }
//
        //initialize heap

        // Create heap of size k
        int maxSize = 6;

        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(maxSize);
        minHeap.insert("a");
        minHeap.insert("b");
        minHeap.insert("c");
        minHeap.insert("d");
        minHeap.minHeap();

        minHeap.print();
        System.out.println("The Min val is " + minHeap.remove());

//        System.out.println("The Min Heap is ");
//        MinHeapO minHeap = new MinHeapO(15);
//        minHeap.insert(5);
//        minHeap.insert(3);
//        minHeap.insert(17);
//        minHeap.insert(10);
//        minHeap.insert(84);
//        minHeap.insert(19);
//        minHeap.insert(6);
//        minHeap.insert(22);
//        minHeap.insert(9);
//        minHeap.minHeap();
//
//        minHeap.print();
//        System.out.println("The Min val is " + minHeap.remove());

    }
}
