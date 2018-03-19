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
        int maxSize = Integer.parseInt(args[0]);

        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(maxSize);
        minHeap.insert("d");
        minHeap.insert("c");
        minHeap.insert("b");
        minHeap.insert("a");
        minHeap.minHeap();

        minHeap.print();
        System.out.println("The Min val is " + minHeap.remove());

    }
}
