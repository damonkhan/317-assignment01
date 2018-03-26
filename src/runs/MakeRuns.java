//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Authors: Damon Khan & Riley Cochrane                                     //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


package runs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class MakeRuns {

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: <size> <textFile>");
            return;
        }

        try {

            // Initialize all the parts...
            int maxSize = Integer.parseInt(args[0]) + 1;
            String path = "/Users/damonkhan/Desktop/" + args[1];
            MinHeap minHeap = new MinHeap(maxSize);
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("Error: file not found");
                return;
            }

            FileReader fReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fReader);

            // Build the heap
            for (int i = 1; i < maxSize; i++)
            {
                minHeap.insert(reader.readLine());
            }

            Heapify(minHeap);
            System.out.println("The Min Heap is ");
            minHeap.print();
            System.out.println("Next out is " + minHeap.minValue());

            minHeap.replace("g");
            System.out.println("The Min Heap is ");
            minHeap.print();
            System.out.println("Next out is " + minHeap.minValue());

        }
        catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void Heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }

    }
}
